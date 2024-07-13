package com.pratopronto.config;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/pagamentocommons",
        plugin = {"pretty", "html:target/cucumber/pagamentocommons"},
        extraGlue = "com.pratopronto.pagamentocommons")
public class PagamentoCucumberIntegrationTest {
}
