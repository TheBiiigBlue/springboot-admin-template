package com.thebigblue.web.mock;

import com.github.jsonzou.jmockdata.JMockData;
import com.github.jsonzou.jmockdata.MockConfig;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MockDataService {

    private static MockConfig mockConfig;

    static {
        mockConfig = new MockConfig()
                // 全局配置
                .globalConfig()
                .intRange(1, 100000)
                .doubleRange(0.0, 100000)
                .longRange(1, 1000000)
                .dateRange("2020-01-01", "2023-12-31")
                // 全局配置
                .globalConfig();
    }

    /**
     * mock数据
     */
    public static <T> T mock(Class<T> clazz) {
        return JMockData.mock(clazz, mockConfig);
    }

    /**
     * 指定配置mock数据
     */
    public static <T> T mock(Class<T> clazz, MockConfig config) {
        return JMockData.mock(clazz, config);
    }


    /**
     * mock指定数量数据
     */
    public static <T> List<T> mock(Class<T> clazz, int num) {
        return IntStream.range(0, num).boxed().map(index -> JMockData.mock(clazz, mockConfig)).collect(Collectors.toList());
    }

    /**
     * 指定配置指定数量mock数据
     */
    public static <T> List<T> mock(Class<T> clazz, int num, MockConfig config) {
        return IntStream.range(0, num).boxed().map(index -> JMockData.mock(clazz, config)).collect(Collectors.toList());
    }
}
