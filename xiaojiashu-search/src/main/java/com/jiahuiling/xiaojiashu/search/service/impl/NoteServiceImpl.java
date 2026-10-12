package com.jiahuiling.xiaojiashu.search.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import com.jiahuiling.framework.common.response.PageResponse;
import com.jiahuiling.framework.common.util.NumberUtils;
import com.jiahuiling.framework.jackson.constant.DateConstants;
import com.jiahuiling.xiaojiashu.search.index.NoteIndex;
import com.jiahuiling.xiaojiashu.search.model.vo.SearchNoteReqVO;
import com.jiahuiling.xiaojiashu.search.model.vo.SearchNoteRspVO;
import com.jiahuiling.xiaojiashu.search.service.NoteService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.lucene.search.function.CombineFunction;
import org.elasticsearch.common.lucene.search.function.FieldValueFactorFunction;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FieldValueFactorFunctionBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @description: 用户搜索业务
 **/
@Service
@Slf4j
public class NoteServiceImpl implements NoteService {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    /**
     * 搜索笔记
     *
     * @param searchNoteReqVO
     * @return
     */
    @Override
    public PageResponse<SearchNoteRspVO> searchNote(SearchNoteReqVO searchNoteReqVO) {
        // 查询关键词
        String keyword = searchNoteReqVO.getKeyword();
        // 当前页码
        Integer pageNo = searchNoteReqVO.getPageNo();

        // 构建 SearchRequest，指定要查询的索引
        SearchRequest searchRequest = new SearchRequest(NoteIndex.NAME);

        // 创建查询构建器
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        // 创建查询条件
        //       "query": {
        //         "multi_match": {
        //           "query": "壁纸",
        //           "fields": ["title^2", "topic"]
        //         }
        //       },
        QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(keyword)
                .field(NoteIndex.FIELD_NOTE_TITLE, 2.0f) // 手动设置笔记标题的权重值为 2.0
                .field(NoteIndex.FIELD_NOTE_TOPIC) // 不设置，权重默认为 1.0
                ;
        // 创建 FilterFunctionBuilder 数组
        // "functions": [
        //         {
        //           "field_value_factor": {
        //             "field": "like_total",
        //             "factor": 0.5,
        //             "modifier": "sqrt",
        //             "missing": 0
        //           }
        //         },
        //         {
        //           "field_value_factor": {
        //             "field": "collect_total",
        //             "factor": 0.3,
        //             "modifier": "sqrt",
        //             "missing": 0
        //           }
        //         },
        //         {
        //           "field_value_factor": {
        //             "field": "comment_total",
        //             "factor": 0.2,
        //             "modifier": "sqrt",
        //             "missing": 0
        //           }
        //         }
        //       ],
        FunctionScoreQueryBuilder.FilterFunctionBuilder[] filterFunctionBuilders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[]{
                new FunctionScoreQueryBuilder.FilterFunctionBuilder(
                        new FieldValueFactorFunctionBuilder(NoteIndex.FIELD_NOTE_LIKE_TOTAL)
                                .factor(0.5f)
                                .modifier(FieldValueFactorFunction.Modifier.SQRT)
                                .missing(0)
                ),
                new FunctionScoreQueryBuilder.FilterFunctionBuilder(
                        new FieldValueFactorFunctionBuilder(NoteIndex.FIELD_NOTE_COLLECT_TOTAL)
                                .factor(0.3f)
                                .modifier(FieldValueFactorFunction.Modifier.SQRT)
                                .missing(0)
                ),
                new FunctionScoreQueryBuilder.FilterFunctionBuilder(
                        new FieldValueFactorFunctionBuilder(NoteIndex.FIELD_NOTE_COMMENT_TOTAL)
                                .factor(0.2f)
                                .modifier(FieldValueFactorFunction.Modifier.SQRT)
                                .missing(0)
                ),
        };
        // 构建 function_score 查询
        // "score_mode": "sum",
        // "boost_mode": "sum"
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(queryBuilder, filterFunctionBuilders)
                .scoreMode(FunctionScoreQuery.ScoreMode.SUM)
                .boostMode(CombineFunction.SUM);

        // 设置查询
        sourceBuilder.query(functionScoreQueryBuilder);

        // 设置排序
        // "sort": [
        //     {
        //       "_score": {
        //         "order": "desc"
        //       }
        //     }
        //   ]
        sourceBuilder.sort(new FieldSortBuilder("_score").order(SortOrder.DESC));

        // 设置分页，from 和 size
        int pageSize = 10; // 每页展示数据量
        int from = (pageNo - 1) * pageSize; // 偏移量
        sourceBuilder.from(from);
        sourceBuilder.size(pageSize);

        // 设置高亮字段
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field(NoteIndex.FIELD_NOTE_TITLE)
                .preTags("<strong>")
                .postTags("</strong>");
        sourceBuilder.highlighter(highlightBuilder);

        // 将构建的查询条件设置到 SearchRequest 中
        searchRequest.source(sourceBuilder);

        // 返参 VO 集合
        List<SearchNoteRspVO> searchNoteRspVOS = null;
        // 总文档数，默认为 0
        long total = 0;
        try {
            log.info("==> SearchRequest: {}", searchRequest);
            // 执行搜索
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

            // 处理搜索结果
            total = searchResponse.getHits().getTotalHits().value;
            log.info("==> 命中文档总数, hits: {}", total);

            searchNoteRspVOS = Lists.newArrayList();

            // 获取搜索命中的文档列表
            SearchHits hits = searchResponse.getHits();

            for (SearchHit hit : hits) {
                log.info("==> 文档数据: {}", hit.getSourceAsString());

                // 获取文档的所有字段（以 Map 的形式返回）
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();


                // 提取特定字段值
                Long noteId = (Long) sourceAsMap.get(NoteIndex.FIELD_NOTE_ID);
                String cover = (String) sourceAsMap.get(NoteIndex.FIELD_NOTE_COVER);
                String title = (String) sourceAsMap.get(NoteIndex.FIELD_NOTE_TITLE);
                String avatar = (String) sourceAsMap.get(NoteIndex.FIELD_NOTE_AVATAR);
                String nickname = (String) sourceAsMap.get(NoteIndex.FIELD_NOTE_NICKNAME);
                // 获取更新时间
                String updateTimeStr = (String) sourceAsMap.get(NoteIndex.FIELD_NOTE_UPDATE_TIME);
                LocalDateTime updateTime = LocalDateTime.parse(updateTimeStr, DateConstants.DATE_FORMAT_Y_M_D_H_M_S);
                Integer likeTotal = (Integer) sourceAsMap.get(NoteIndex.FIELD_NOTE_LIKE_TOTAL);

                // 获取高亮字段
                String highlightedTitle = null;
                if (CollUtil.isNotEmpty(hit.getHighlightFields())
                        && hit.getHighlightFields().containsKey(NoteIndex.FIELD_NOTE_TITLE)) {
                    highlightedTitle = hit.getHighlightFields().get(NoteIndex.FIELD_NOTE_TITLE).fragments()[0].string();
                }

                // 构建 VO 实体类
                SearchNoteRspVO searchNoteRspVO = SearchNoteRspVO.builder()
                        .noteId(noteId)
                        .cover(cover)
                        .title(title)
                        .highlightTitle(highlightedTitle)
                        .avatar(avatar)
                        .nickname(nickname)
                        .updateTime(updateTime)
                        .likeTotal(NumberUtils.formatNumberString(likeTotal))
                        .build();
                searchNoteRspVOS.add(searchNoteRspVO);
            }
        } catch (
                IOException e) {
            log.error("==> 查询 Elasticserach 异常: ", e);
        }

        return PageResponse.success(searchNoteRspVOS, pageNo, total);
    }
}

