<?php

use Illuminate\Database\Seeder;

class RecipeSupplementsSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('recipe_supplements')->insert([
            'recipe_supplement_id'=> '1wwweqe32',
            'user_name' => 'maurico',
            'supplement_name'=> 'salt&pepper',
            'supplement_amount'=> 2,
            'supplement_amount_format'=> 'tbsp'
        ]);

        DB::table('recipe_supplements')->insert([
            'recipe_supplement_id'=> '1wwweqe32',
            'user_name' => 'maurico',
            'supplement_name'=> 'sugar',
            'supplement_amount'=> 5,
            'supplement_amount_format'=> 'tbsp'
        ]);
        
        DB::table('recipe_supplements')->insert([
            'recipe_supplement_id'=> '1awweqe32',
            'user_name' => 'apyr',
            'supplement_name'=> 'syrup',
            'supplement_amount'=> 3,
            'supplement_amount_format'=> 'tbsp'
            
        ]);
        DB::table('recipe_supplements')->insert([
            'recipe_supplement_id'=> '1awweqe32',
            'user_name' => 'apyr',
            'supplement_name'=> 'honey',
            'supplement_amount'=> 3,
            'supplement_amount_format'=> 'tbsp'
        ]);

        DB::table('recipe_supplements')->insert([
            'recipe_supplement_id'=> '1ewweqe32',
            'user_name' => 'apyr',
            'supplement_name'=> 'milk',
            'supplement_amount'=> 0.3,
            'supplement_amount_format'=> 'l'
        ]);
        DB::table('recipe_supplements')->insert([
            'recipe_supplement_id'=> '1ewweqe32',
            'user_name' => 'apyr',
            'supplement_name'=> 'water',
            'supplement_amount'=> 0.5,
            'supplement_amount_format'=> 'l'
        ]);
    }
}
