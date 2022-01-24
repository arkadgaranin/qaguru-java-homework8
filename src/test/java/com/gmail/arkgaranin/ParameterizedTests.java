package com.gmail.arkgaranin;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ParameterizedTests extends TestBase {

  @ValueSource(strings = {"QA automation engineer", "Manual QA"})
  @ParameterizedTest(name = "Тестирование поиска вакансий на hh.ru с тестовыми данными: {0}")
  void searchAutomationVacancyOnHhTest1(String testData) {
    open("/");
    $("[data-qa=search-input]").setValue(testData);
    $("[data-qa=search-button]").click();
    $("[data-qa=vacancy-serp__results] > div span[data-qa=bloko-header-3]").shouldHave(text(testData));
  }

  @CsvSource(value = {
      "QA automation engineer, Разработка автоматизированных тестов",
      "Manual QA, Review and analyze system specifications"
  })

  @ParameterizedTest(name = "Тестирование поиска вакансий на hh.ru с тестовыми данными: {0}")
  void searchAutomationVacancyOnHhTest2(String testData, String expectedResult) {
    open("/");
    $("[data-qa=search-input]").setValue(testData);
    $("[data-qa=search-button]").click();
    $("[data-qa=vacancy-serp__results] > div [data-qa=vacancy-serp__vacancy_snippet_responsibility] > span").
        shouldHave(text(expectedResult));
  }

  static Stream<Arguments> dataProvider() {
    return Stream.of(
        Arguments.of("QA automation engineer", "Опыт автоматизации тестирования"),
        Arguments.of("Manual QA", "2 years of experience in testing")
    );
  }

  @MethodSource("dataProvider")
  @ParameterizedTest(name = "Тестирование поиска вакансий на hh.ru с тестовыми данными: {0}")
  void searchAutomationVacancyOnHhTest3(String testData, String expectedResult) {
    open("/");
    $("[data-qa=search-input]").setValue(testData);
    $("[data-qa=search-button]").click();
    $("[data-qa=vacancy-serp__results] > div [data-qa=vacancy-serp__vacancy_snippet_requirement] > span").
        shouldHave(text(expectedResult));
  }
}
