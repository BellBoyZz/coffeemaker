package edu.ncsu.csc326.coffeemaker;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

public class CoffeeMakerCucumberTest {

    /**
     * The object under test.
     */
    private CoffeeMaker coffeeMaker;
    private CoffeeMaker coffeeMakerMock;
    private Inventory inventory;
    private RecipeBook recipeBook;

    // Sample recipes to use in testing.
    private Recipe recipe1;
    private Recipe recipe2;
    private Recipe recipe3;
    private Recipe recipe4;
    private Recipe[] recipeList;

    /**
     * Initializes some recipes to test with and the {@link CoffeeMaker}
     * object we wish to test.
     *
     * @throws RecipeException if there was an error parsing the ingredient
     *                         amount when setting up the recipe.
     */
    @Given("Coffee maker is on service and ready to serve.")
    public void coffeeMakerIsOnServiceAndReadyToServe() throws RecipeException{
        inventory = new Inventory();
        recipeBook = mock(RecipeBook.class);
        coffeeMaker = new CoffeeMaker();
        coffeeMakerMock = new CoffeeMaker(recipeBook, inventory);

        //Set up for r1.
        recipe1 = new Recipe();
        recipe1.setName("Coffee");
        recipe1.setAmtChocolate("0");
        recipe1.setAmtCoffee("3");
        recipe1.setAmtMilk("1");
        recipe1.setAmtSugar("1");
        recipe1.setPrice("50");

        //Set up for r2
        recipe2 = new Recipe();
        recipe2.setName("Mocha");
        recipe2.setAmtChocolate("20");
        recipe2.setAmtCoffee("3");
        recipe2.setAmtMilk("1");
        recipe2.setAmtSugar("1");
        recipe2.setPrice("75");

        //Set up for r3
        recipe3 = new Recipe();
        recipe3.setName("Latte");
        recipe3.setAmtChocolate("0");
        recipe3.setAmtCoffee("3");
        recipe3.setAmtMilk("3");
        recipe3.setAmtSugar("1");
        recipe3.setPrice("100");

        //Set up for r4
        recipe4 = new Recipe();
        recipe4.setName("Hot Chocolate");
        recipe4.setAmtChocolate("4");
        recipe4.setAmtCoffee("0");
        recipe4.setAmtMilk("1");
        recipe4.setAmtSugar("1");
        recipe4.setPrice("65");

        // all recipe in a list
        recipeList = new Recipe[]{recipe1, recipe2, recipe3, recipe4};
    }

    @When("The customer order an invalid coffee recipe.")
    public void theCustomerOrderAnInvalidCoffeeRecipe() {
        when(coffeeMakerMock.getRecipes()).thenReturn(new Recipe[1]);
    }

    @Then("The customer order a coffee with a recipe number {int}, with money {int}, then change equal to {int}.")
    public void theCustomerOrderACoffeeWithARecipeNumberWithMoneyThenChangeEqualTo(Integer recipeID, Integer money, Integer change) {
        assertEquals(change.intValue(), coffeeMakerMock.makeCoffee(recipeID, money));
    }

    @When("The customer order a coffee with not enough money.")
    public void theCustomerOrderACoffeeWithNotEnoughMoney() {
        when(coffeeMakerMock.getRecipes()).thenReturn(recipeList);
    }
}
