package com.itheima.es;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.TermVectorsResponse;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.client.ml.EvaluateDataFrameRequest;
import org.elasticsearch.cluster.metadata.MappingMetadata;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.xcontent.XContentBuilder;
import org.elasticsearch.xcontent.XContentType;
import org.elasticsearch.xcontent.json.JsonXContent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zy136
 * @date 2021/12/23 17:46
 */
public class EsDemo {

    private RestHighLevelClient client;

    @Before
    public void init() {
        client = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.115.128", 9200)));
    }

    @After
    public void destroy() throws IOException {
        client.close();
    }

    //fixme:???????????????

    @Test//???????????????
    public void testIndexCreate() throws IOException {
        CreateIndexResponse response = client.indices().create(new CreateIndexRequest("user"), RequestOptions.DEFAULT);
        if (response.isAcknowledged()) {
            System.out.println("????????????");
        }
    }

    @Test//??????????????? ??????????????? ??????????????????
    public void testIndexCreatePro() throws IOException {
        CreateIndexRequest user = new CreateIndexRequest("user");
        //???????????????
        user.settings(Settings.builder().put("index.number_of_shards", 3).put("index.number_of_replicas", 1));
        //????????????
        user.mapping(" {\n" +
                "    \"properties\": {\n" +
                "      \"id\": {\n" +
                "        \"type\": \"long\"\n" +
                "      },\n" +
                "      \"name\":{\n" +
                "        \"type\": \"keyword\"\n" +
                "      },\n" +
                "      \"age\":{\n" +
                "        \"type\": \"integer\"\n" +
                "      },\n" +
                "      \"gender\":{\n" +
                "        \"type\": \"keyword\"\n" +
                "      },\n" +
                "      \"note\":{\n" +
                "        \"type\": \"text\",\n" +
                "        \"analyzer\": \"ik_max_word\"\n" +
                "      }\n" +
                "    }\n" +
                "  }", XContentType.JSON);
        CreateIndexResponse response = client.indices().create(user, RequestOptions.DEFAULT);

        if (response.isAcknowledged()) {
            System.out.println("????????????");
        }
    }


    @Test//???????????????
    public void testGetIndex() throws IOException {
        GetIndexResponse response = client.indices().get(new GetIndexRequest("car"), RequestOptions.DEFAULT);
        Map<String, Settings> settings = response.getSettings();
        System.out.println("???????????????????????????" + settings);
        Map<String, MappingMetadata> mappings = response.getMappings();
        MappingMetadata car = mappings.get("car");
        System.out.println(car.source());
    }

    @Test//???????????????
    public void testDeleteIndex() throws IOException {
        AcknowledgedResponse response = client.indices().delete(new DeleteIndexRequest("user"), RequestOptions.DEFAULT);
        if (response.isAcknowledged()) {
            System.out.println("????????????");
        }
    }

    //fixme:????????????

    @Test//??????id????????????
    public void testAddDoc() throws IOException {
        IndexRequest indexRequest = new IndexRequest("user");
        indexRequest.id("1");
        indexRequest.source("{\n" +
                "    \"age\" : 18,\n" +
                "    \"gender\" :\"???\",\n" +
                "    \"name\" : \"?????????\",\n" +
                "    \"note\" : \"????????????.\"\n" +
                "}", XContentType.JSON);
        IndexResponse response = client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(response.getResult());
    }

    @Test//??????id????????????
    public void testAddDocPro() throws IOException {
        User user = new UserService().findById(11L);
        String userJson = JSON.toJSONString(user);
        System.out.println(userJson);

        IndexRequest indexRequest = new IndexRequest("user");
        indexRequest.id(user.getId().toString());
        indexRequest.source(userJson, XContentType.JSON);
        IndexResponse response = client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(response.getResult());

    }

    @Test//??????id????????????
    public void testGetDoc() throws IOException {
        GetRequest getRequest = new GetRequest("user", "11");
        GetResponse docs = client.get(getRequest, RequestOptions.DEFAULT);
        String userJson = docs.getSourceAsString();
        User user = JSON.parseObject(userJson, User.class);
        System.out.println(user);

    }

    @Test//??????id????????????
    public void testUpdateDoc() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("user", "11");
        updateRequest.doc("gender", "???");
        UpdateResponse response = client.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(response.getResult());

    }

    @Test//??????id????????????
    public void testDeleteDoc() throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest("user", "1");
        DeleteResponse response = client.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println(response.getResult());

    }

    @Test//??????????????????
    public void testBulkDoc() throws IOException {
        List<User> userList = new UserService().findAll();
        BulkRequest bulkRequest = new BulkRequest("user");
        for (User user : userList) {
            bulkRequest.add(new IndexRequest()
                    .id(user.getId().toString())
                    .source(JSON.toJSONString(user), XContentType.JSON));
        }
        BulkResponse response = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(response.status());

    }

    @Test//????????????????????????(match_all) ????????? ?????????????????????4?????????
    public void testSearchMatchAllDoc() throws IOException {
        SearchRequest searchRequest = new SearchRequest("user");
        searchRequest.source(SearchSourceBuilder.searchSource()
                .query(QueryBuilders.matchAllQuery())
                .sort("id", SortOrder.ASC)
                .from(0).size(4));
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        for (SearchHit hit : hits.getHits()) {
            System.out.println(JSON.parseObject(hit.getSourceAsString(), User.class));
        }

    }

    @Test//???????????? match
    public void testSearchMatchDoc() throws IOException {
        SearchRequest searchRequest = new SearchRequest("user");
        searchRequest.source(SearchSourceBuilder.searchSource()
                .query(QueryBuilders.matchQuery("note", "JAVA"))//???????????????,??????????????????
                .sort("id", SortOrder.ASC));
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        for (SearchHit hit : hits.getHits()) {
            System.out.println(JSON.parseObject(hit.getSourceAsString(), User.class));
        }

    }

    @Test//???????????? term
    public void testSearchTermDoc() throws IOException {
        SearchRequest searchRequest = new SearchRequest("user");
        searchRequest.source(SearchSourceBuilder.searchSource()
                .query(QueryBuilders.termQuery("note", "??????")));//???????????????,??????????????????
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        for (SearchHit hit : hits.getHits()) {
            System.out.println(JSON.parseObject(hit.getSourceAsString(), User.class));
        }

    }

    @Test//???????????? bool
    public void testSearchBoolDoc() throws IOException {
        SearchRequest searchRequest = new SearchRequest("user");
        searchRequest.source(SearchSourceBuilder.searchSource()
                .query(QueryBuilders.boolQuery()//????????????
                        .must(QueryBuilders.matchQuery("note", "???"))//??????:match note?????????
                        .mustNot(QueryBuilders.rangeQuery("age").gte(25).lte(50))//?????????(??????): ???????????? 20-50 age??????
                        .filter(QueryBuilders.matchQuery("note", "java"))));//??????java???????????????
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        for (SearchHit hit : hits.getHits()) {
            System.out.println(JSON.parseObject(hit.getSourceAsString(), User.class));
        }

    }

    @Test//source ??????????????????????????????
    public void testSearchSourceDoc() throws IOException {
        SearchRequest searchRequest = new SearchRequest("user");
        searchRequest.source(SearchSourceBuilder.searchSource()
                .query(QueryBuilders.matchAllQuery()).fetchSource(new String[]{"name", "age", "gender"}, null));//????????????,???????????????//excludes:
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        for (SearchHit hit : hits.getHits()) {
            System.out.println(JSON.parseObject(hit.getSourceAsString(), User.class));
        }

    }

    @Test//highlight ????????????
    public void testSearchHighlightDoc() throws IOException {
        //?????????????????? ?????????????????????
        SearchRequest searchRequest = new SearchRequest("user");
        //???????????????
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //???????????????
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        //?????????????????????????????????
        highlightBuilder.field("note").highlighterType("unified");
        //?????????????????????????????????,?????????????????????
        searchSourceBuilder.highlighter(highlightBuilder).query(QueryBuilders.matchQuery("note", "java"));
        //?????????????????????????????????
        searchRequest.source(searchSourceBuilder);
        //????????????
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        //????????????
        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getHighlightFields());
        }

    }

    @Test//aggs ????????????
    public void testSearchAggsDoc() throws IOException {
        SearchRequest searchRequest = new SearchRequest("user");
        searchRequest.source(SearchSourceBuilder.searchSource()
                .query(QueryBuilders.matchAllQuery())
                .aggregation(AggregationBuilders
                        .terms("gender_agg").field("gender")));
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        Aggregations aggregations = response.getAggregations();
        Terms gender_agg = aggregations.get("gender_agg");
        List<? extends Terms.Bucket> buckets = gender_agg.getBuckets();
        for (Terms.Bucket bucket : buckets) {
            System.out.println(bucket.getKeyAsString() + "??????:" + bucket.getDocCount());
        }

    }


}
