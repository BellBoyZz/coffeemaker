/*
 * Copyright (c) 2009,  Sarah Heckman, Laurie Williams, Dright Ho
 * All Rights Reserved.
 *
 * Permission has been explicitly granted to the University of Minnesota
 * Software Engineering Center to use and distribute this source for
 * educational purposes, including delivering online education through
 * Coursera or other entities.
 *
 * No warranty is given regarding this software, including warranties as
 * to the correctness or completeness of this software, including
 * fitness for purpose.
 *
 *
 * Modifications
 * 20171114 - Ian De Silva - Updated to comply with JUnit 4 and to adhere to
 * 							 coding standards.  Added test documentation.
 */
package edu.ncsu.csc326.coffeemaker;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

import static org.junit.Assert.*;

/**
 * Unit tests for CoffeeMaker class.
 *
 * @author Peerasu Watanasirang
 */
public class CoffeeMakerTest {

	/**
	 * The object under test.
	 */
	private CoffeeMaker coffeeMaker;

	// Sample recipes to use in testing.
	private Recipe recipe1;
	private Recipe recipe2;
	private Recipe recipe3;
	private Recipe recipe4;

	/**
	 * Initializes some recipes to test with and the {@link CoffeeMaker}
	 * object we wish to test.
	 *
	 * @throws RecipeException  if there was an error parsing the ingredient
	 * 		amount when setting up the recipe.
	 */
	@Before
	public void setUp() throws RecipeException {
		coffeeMaker = new CoffeeMaker();

		//Set up for r1
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
	}

	/**
	 * Given a coffee maker with one valid recipe
	 * When we make coffee, each recipe name must be unique in the recipe list
	 */
	@Test
	public void testAddDuplicateRecipe() {
		boolean first_added = coffeeMaker.addRecipe(recipe2);
		assertTrue(first_added);
		boolean second_added = coffeeMaker.addRecipe(recipe2);
		assertFalse(second_added);
	}

	/**
	 * We can only add three recipes to the Coffee Maker
	 * When we try to add the fourth time, it won't allow us to
	 * Then return false.
	 */
	@Test
	public void testAddRecipeMoreThanExceedAmount() {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		coffeeMaker.addRecipe(recipe3);
		assertFalse(coffeeMaker.addRecipe(recipe4));
	}

	/**
	 * Given valid index to delete recipe in the Coffee maker
	 * The deleted recipe will not be in the recipe book.
	 */
	@Test
	public void testDeleteRecipe() {
		coffeeMaker.addRecipe(recipe1);
		assertEquals("Coffee", coffeeMaker.deleteRecipe(0));
	}

	/** Given an empty recipe book
	 * When we try to delete the recipe, it should return null (because the recipe book is now empty).
	 */
	@Test
	public void testDeleteRecipeWhenRecipeBookIsEmpty() {
		assertNull(coffeeMaker.deleteRecipe(0));
	}

	/**
	 * Given one recipe in Coffee Maker
	 * When try to delete with a negative index, it should return null
	 */
	@Test
	public void testDeleteRecipeWithNegativeIndexRecipe() {
		coffeeMaker.addRecipe(recipe1);
		String delete_recipe = coffeeMaker.deleteRecipe(-1);
		assertNull(delete_recipe);
	}

	/**
	 * Given one recipe in Coffee Maker
	 * When try to delete with invalid index (Out of bound), it should return null
	 */
	@Test
	public void testDeleteRecipeWithOutOfBoundIndexRecipe() {
		coffeeMaker.addRecipe(recipe1);
		String delete_recipe = coffeeMaker.deleteRecipe(10);
		assertNull(delete_recipe);
	}

	/**
	 * User enter the new recipe portion. A recipe name must not changed.
	 */
	@Test
	public void testEditRecipe() {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.editRecipe(0, recipe3);
		Recipe[] recipes = coffeeMaker.getRecipes();
		assertEquals(recipes[0], recipe3);
	}

	/**
	 * Given an empty recipe book
	 * When we try to edit the recipe, it should return null (because the recipe book is now empty)
	 */
	@Test
	public void testEditRecipeWhenRecipeBookIsEmpty() {
		String recipe_book = coffeeMaker.editRecipe(0, recipe1);
		assertNull(recipe_book);
	}

	/**
	 * Given one recipe in the Coffee Maker
	 * When try to edit the recipe with a negative index, it should return null
	 */
	@Test
	public void testEditRecipeWithNegativeIndexRecipe() {
		coffeeMaker.addRecipe(recipe1);
		String edited_recipe = coffeeMaker.editRecipe(-1, recipe2);
		assertNull(edited_recipe);
	}

	/**
	 * Given one recipe in Coffee Maker
	 * When try to edit with invalid index (out of bound), it should return null
	 */
	@Test
	public void testEditRecipeWithOutOfBoundIndexRecipe() {
		coffeeMaker.addRecipe(recipe1);
		String edited_recipe = coffeeMaker.editRecipe(20, recipe2);
		assertNull(edited_recipe);
	}

	/**
	 * Given a coffee maker with the default inventory
	 * When we add inventory with well-formed quantities
	 * Then we do not get an exception trying to read the inventory quantities.
	 *
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test
	public void testAddInventory() throws InventoryException {
		coffeeMaker.addInventory("4","7","0","9");
		coffeeMaker.addInventory("0", "0", "1", "0");
		coffeeMaker.addInventory("0", "0", "0", "1");
	}

	/**
	 * Given a coffee maker with the default inventory
	 * When we add inventory with malformed quantities (i.e., a negative
	 * quantity and a non-numeric string)
	 * Then we get an inventory exception
	 *
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test(expected = InventoryException.class)
	public void testAddInventoryException() throws InventoryException {
		coffeeMaker.addInventory("4", "-1", "asdf", "3");
		coffeeMaker.addInventory("-2", "3", "2", "1");
		coffeeMaker.addInventory("Sam", "Red", "Yea", "Hello");
	}

	/**
	 * When we add items to inventory with well-formed quantities
	 * Then inventory should be update the quantities.
	 */
	@Test
	public void testCheckInventory() throws InventoryException {
		assertEquals("Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n",coffeeMaker.checkInventory());
	}

	/**
	 * When we make a coffee, if there is not enough inventory to make a coffee
	 * Then we will get our money back.
	 */
	@Test
	public void testNotEnoughInventory() {
		coffeeMaker.addRecipe(recipe2);
		int change = coffeeMaker.makeCoffee(0, 75);
		assertEquals(75, change);
	}

	/**
	 * Given a coffee maker with one valid recipe
	 * When we make coffee, selecting the valid recipe and paying more than
	 * 		the coffee costs
	 * Then we get the correct change back.
	 */
	@Test
	public void testMakeCoffee() {
		coffeeMaker.addRecipe(recipe1);
		assertEquals(25, coffeeMaker.makeCoffee(0, 75));
	}

	/**
	 * When we make a coffee, select the valid recipe and paying less than the coffee costs
	 * Then we will get our money back.
	 */
	@Test
	public void testPaidLessThanCoffeeCost() {
		coffeeMaker.addRecipe(recipe2);
		assertEquals(50, coffeeMaker.makeCoffee(0, 50));
	}

	/**
	 * When we make a coffee, select the valid recipe and paying equal to the coffee costs
	 * Then we will not get any change.
	 */
	@Test
	public void testPaidEqualToCoffeeCost() {
		coffeeMaker.addRecipe(recipe1);
		assertEquals(0, coffeeMaker.makeCoffee(0, 50));
	}

	/**
	 * When we make a coffee, select the valid recipe and paying more than the coffee costs
	 * Then we will get a change.
	 */
	@Test
	public void testPaidMoreThanCoffeeCost() {
		coffeeMaker.addRecipe(recipe3);
		assertEquals(20, coffeeMaker.makeCoffee(0, 120));
	}

	/**
	 * Make a coffee with negative index
	 * makeCoffee() should handle negative argument and not make any error, then return something.
	 *
	 */
	@Test
	public void testMakeCoffeeWithNegativeIndexRecipe() {
		coffeeMaker.addRecipe(recipe1);
		int change = coffeeMaker.makeCoffee(-1, 100);
		assertEquals(100, change);
	}

	/**
	 * Make a coffee with an invalid index (Out of bound)
	 * makeCoffee() should handle out of bound argument and not make any error, then return something.
	 */
	@Test
	public void testMakeCoffeeWithOutOfBoundIndexRecipe() {
		coffeeMaker.addRecipe(recipe1);
		int change = coffeeMaker.makeCoffee(10, 100);
		assertEquals(100, change);
	}

	/**
	 * Make a coffee with null recipe
	 * Then we get our money back.
	 */
	@Test
	public void testMakeCoffeeWithNullRecipe() {
		int change = coffeeMaker.makeCoffee(0, 100);
		assertEquals(100, change);
	}
}
