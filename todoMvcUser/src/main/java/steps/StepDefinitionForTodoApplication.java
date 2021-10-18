package steps;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StepDefinitionForTodoApplication {

	// declaring chrome driverObj
	public static ChromeDriver driverObj;

	// declaring ArrayList for all the Buttons
	static List<String> allItemsList = new ArrayList<String>();
	static List<String> activeItemsList = new ArrayList<String>();
	static List<String> selectedItemsList = new ArrayList<String>();
	static List<String> unSelectedItemsList = new ArrayList<String>();
	static List<String> toDoItemList = new ArrayList<String>();
	static List<String> completedItemList = new ArrayList<String>();
	public static WebElement allButton;

	@Given("Open the browser")
	public static void openBrowser() {
		WebDriverManager.chromedriver().setup();
		driverObj = new ChromeDriver();
		driverObj.manage().window().maximize();
		driverObj.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	// Launching the application url
	@Given("Load the application URL {string}")
	public static void loadURL(String url) {
		driverObj.get(url);
	}

	// Creating To-Do tasks by entering in What needs to be done Textbox
	@Given("Enter the Todoitem as {string}")
	public static void enterToDOItem(String todoitem) {

		WebElement inputText = driverObj.findElement(By.xpath("//input[@class='new-todo']"));
		inputText.sendKeys(todoitem);
		inputText.sendKeys(Keys.ENTER);
		toDoItemList.add(todoitem);

	}

	// View All Assigned To-Do task item.
	// Verify if the items entered matches the task list passed in examples
	@When("Click on the All button")
	public static void clickAllButton() {

		allButton = driverObj.findElement(By.linkText("All"));
		allButton.click();
		List<WebElement> allWebItemsList = driverObj.findElements(By.xpath("//ul[@class='todo-list']/li"));
		for (WebElement webElement : allWebItemsList) {
			allItemsList.add(webElement.getText());
		}
	}

	// Verification using Assert
	@Then("Verify All todoitems passed in examples are added under All tasks")
	public static void verifyAllItems() {
		Assert.assertEquals(toDoItemList, allItemsList);
	}

	// Complete all assigned tasks
	@Given("Click on the Radio button to complete the task")
	public static void clickOnRadioButtonToCompleteTask() throws InterruptedException {

		for (int i = 1; i <= allItemsList.size(); i++) {
			boolean selected = driverObj
					.findElement(By.xpath("(//ul[@class='todo-list']//input[@type='checkbox'])[" + i + "]"))
					.isSelected();
			if (!selected) {
				driverObj.findElement(By.xpath("(//ul[@class='todo-list']//input[@type='checkbox'])[" + i + "]"))
						.click();
				Thread.sleep(500);
			}
		}
	}

	// click on the completed button
	@When("Click on completed button")
	public static void clickCompletedButton() throws InterruptedException {
		Thread.sleep(2000);
		driverObj.findElement(By.linkText("Completed")).click();
		List<WebElement> completedWebItemsList = driverObj.findElements(By.xpath("//ul[@class='todo-list']/li"));
		for (WebElement webElement : completedWebItemsList) {
			completedItemList.add(webElement.getText());
		}

	}

	// Verify All selected tasks moved under completed
	@Then("Verify all the selected items moved under completed")
	public static void verifyCompletedItems() throws InterruptedException {
		Thread.sleep(2000);

		int completedSize = completedItemList.size();
		int allSize = allItemsList.size();

		String itemsLeft = driverObj.findElement(By.tagName("strong")).getText();
		int itemsLeftInNumber = Integer.parseInt(itemsLeft);

		Assert.assertEquals(completedSize, allSize - itemsLeftInNumber);

	}

	// Check Active button after completing all the tasks
	@When("Click Active button to check any pending task")
	public static void clickActiveButton() {
		driverObj.findElement(By.linkText("Active")).click();
		List<WebElement> activeWebItemsList = driverObj.findElements(By.xpath("//ul[@class='todo-list']/li"));
		for (WebElement webElement : activeWebItemsList) {
			activeItemsList.add(webElement.getText());
		}
	}

	// In this scenario all the tasks are completed so ActiveItems should be empty
	@Then("Verify the activeItem List")
	public static void verifyActiveItems() throws InterruptedException {
		Thread.sleep(2000);

		activeItemsList.size();

		String itemsLeft = driverObj.findElement(By.tagName("strong")).getText();
		int itemsLeftInNumber = Integer.parseInt(itemsLeft);

		Assert.assertEquals(activeItemsList.size(), itemsLeftInNumber);

	}

	// In this scenario all the tasks are completed so ActiveItems should be empty
	@Then("ActiveList should be empty")
	public static void verifyEmptyActiveItems() throws InterruptedException {

		Assert.assertEquals(activeItemsList.size(), 0);

	}

	// click on clear completed(Completed List should get cleared)
	@When("Clear Completed button is Clicked")
	public static void clickClearCompletedButton() throws InterruptedException {
		WebElement clearComp = driverObj.findElement(By.xpath("//button[@class='clear-completed']"));

		if (clearComp.isDisplayed()) {
			clearComp.click();
		}
	}

	//check whether the tasks created are completed
	@Then("Check whether All tasks are completed")
	public static void verifyClearCompletedButton() throws InterruptedException {
		Assert.assertEquals(allButton.isDisplayed(), false);
	}

	//clear completed button
	@Then("Clear Completed button is Clicked for few tasks")
	public static void verifyCompletedItemsCleared() throws InterruptedException {
		clickClearCompletedButton();
		
	}

	
	//completed tasks are cleared
	@Then("Check whether completed tasks are cleared")
	public void check_whether_completed_tasks_are_cleared() {
		allItemsList.clear();
		clickAllButton();

		Assert.assertEquals(activeItemsList.size(), allItemsList.size());

	}

	// Complete First 4 assigned tasks
	@Given("Click on the Radio button to complete few task")
	public static void clickOnRadioButtonToCompleteFewTask() throws InterruptedException {

		for (int i = 0; i <= 3; i++) {
			boolean selected = driverObj
					.findElement(By.xpath("(//ul[@class='todo-list']//input[@type='checkbox'])[" + (i + 1) + "]"))
					.isSelected();
			if (!selected) {
				driverObj.findElement(By.xpath("(//ul[@class='todo-list']//input[@type='checkbox'])[" + (i + 1) + "]"))
						.click();
				Thread.sleep(500);
			}
		}
	}

}
