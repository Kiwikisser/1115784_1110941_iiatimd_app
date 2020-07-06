<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Str;

use App\Recipe;
use App\RecipeSupplement;

class RecipesController extends Controller
{
    public function show(){
        $all_recipes = Recipe::where('recipe_id','!=',null)->get();
        return $all_recipes;
    }
    public function getRecipesFromUserName($username){
        $user_recipes = Recipe::where('user_name','=',$username)->get();
        return $user_recipes;
    }
    public function store(Request $request){
        $data = $request->all();
        $randomId = Str::random(9);
        //$count= 0;
        //$recipeData = [];

        $new_recipe = new Recipe();
        $new_recipe->recipe_id = $randomId;
        $new_recipe->user_name = "evt username get"; 
        $new_recipe->recipe_name = $data[0]['recipe_name']; 
        $new_recipe->recipe_description = $data[0]['recipe_description']; 
        $new_recipe->coffee_bean = $data[0]['coffee_bean'];
        $new_recipe->coffee_volume = $data[0]['coffee_volume'];
        $new_recipe->coffee_roast = $data[0]['coffee_roast'];
        $new_recipe->coffee_prep_time = $data[0]['coffee_prep_time'];
        $new_recipe->save();

        foreach($data[1] as $supplement_data){
            $recipe_supplement = new RecipeSupplement();
            $recipe_supplement->recipe_supplement_id = $randomId;
            $recipe_supplement->user_name = 'evt auth user/variable';
            $recipe_supplement->supplement_name = $supplement_data['supplement_name'];
            $recipe_supplement->supplement_amount = $supplement_data['supplement_amount'];
            $recipe_supplement->supplement_amount_format = $supplement_data['supplement_amount_format'];

            //$recipeData[] = $recipe_supplement;
            $recipe_supplement->save();
        }
        
        return "testresponse";
    }
}
