package edu.ncsu.csc326.coffeemaker;
import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class AddInventoryCucumberTest {

    private CoffeeMaker coffeeMaker;

    @Given("Turn on the coffee maker")
    public void turnOnTheCoffeeMaker() {
        coffeeMaker = new CoffeeMaker();
    }

    @When("I add {int} coffee, add {int} milk, add {int} sugar, add {int} chocolate")
    public void iAddCoffeeAddMilkAddSugarAddChocolate(Integer coffeeAmt, Integer milkAmt, Integer sugarAmt, Integer chocolateAmt) throws InventoryException {
        coffeeMaker.addInventory(coffeeAmt.toString(), milkAmt.toString(), sugarAmt.toString(), chocolateAmt.toString());
    }

    @Then("the inventory will be {int} coffee, {int} milk, {int} sugar, {int} chocolate")
    public void theInventoryWillBeCoffeeMilkSugarChocolate(Integer coffeeAmt, Integer milkAmt, Integer sugarAmt, Integer chocolateAmt) {
        String inventoryMessages = "Coffee: " + coffeeAmt.toString() + "\n" + "Milk: " + milkAmt.toString() + "\n" + "Sugar: " + sugarAmt.toString() + "\n" + "Chocolate: " + chocolateAmt.toString() + "\n";
        assertEquals(inventoryMessages, coffeeMaker.checkInventory());
    }
}
