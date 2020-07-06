<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\RecipeSupplement;

class RecipeSupplementController extends Controller
{
    public function show(){
        $all_supplements = RecipeSupplement::where('recipe_supplement_id','!=',null)->get();
        return $all_supplements;
    }
    public function getSupplementsFromRecipeId($id){
        $recipe_supplements = RecipeSupplement::where('recipe_supplement_id','=',$id)->get();
        return $recipe_supplements;
    }
}
