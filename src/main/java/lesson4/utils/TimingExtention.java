package lesson4.utils;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;

public class TimingExtention implements BeforeTestExecutionCallback, AfterTestExecutionCallback {
    private static Logger logger = LoggerFactory.getLogger(TimingExtention.class);


    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
        long currentTime = System.currentTimeMillis();
        logger.info("Test {} execition time {}", extensionContext.getTestMethod().get().getName(),
                currentTime - (long) extensionContext.getStore(ExtensionContext.Namespace.GLOBAL).get("start time"));
    }

    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) throws Exception {
        extensionContext.getStore(ExtensionContext.Namespace.GLOBAL).put("start time", System.currentTimeMillis());
    }
}
