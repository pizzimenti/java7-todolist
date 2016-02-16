import java.util.ArrayList;

import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.assertj.core.api.Assertions.assertThat;

public class IntegrationTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();





    @Test
    public void rootTest() {
        goTo("http://localhost:4567/");
        assertThat(pageSource()).contains("Task list:");
    }

    @Test
    public void taskIsCreatedTest() {
      goTo("http://0.0.0.0:4567");
      fill("#description").with("Mow the lawn");
      submit(".btn");
      assertThat(pageSource()).contains("Your task has been saved.");
    }

    @Test
    public void taskIsDisplayedTest() {
      goTo("http://0.0.0.0:4567");
      fill("#description").with("Mow the lawn");
      submit(".btn");
      click("a", withText("Go Back"));
      assertThat(pageSource()).contains("Mow the lawn");
      }

    @Test
    public void multipleTasksAreDisplayedTest() {
      goTo("http://0.0.0.0:4567");
      fill("#description").with("Mow the lawn");
      submit(".btn");
      click("a", withText("Go Back"));
      fill("#description").with("Buy groceries");
      submit(".btn");
      click("a", withText("Go Back"));
      assertThat(pageSource()).contains("Mow the lawn");
      assertThat(pageSource()).contains("Buy groceries");
    }

  } //end of Fluent

  /*
  @Test
  public void getChange() {
    goTo("http://localhost:4567");
    fill("#userChange").with("87");
    submit(".btn");
    assertThat(pageSource()).contains("Your change for 87 cents is 3 quarters, 1 dime, 2 pennies.");
  }
  @Test
  public void negativeNumber() {
    goTo("http://localhost:4567");
    fill("#userChange").with("-87");
    submit(".btn");
    assertThat(pageSource()).contains("Please enter a positive value");
  }
*/
