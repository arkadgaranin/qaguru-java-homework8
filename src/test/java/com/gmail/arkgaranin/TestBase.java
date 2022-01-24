package com.gmail.arkgaranin;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

  @BeforeAll
  static void setup() {
    Configuration.browserSize = "1920x1080";
  }
}
