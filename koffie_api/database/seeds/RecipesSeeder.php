<?php

use Illuminate\Database\Seeder;

class RecipesSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('recipes')->insert([
            'recipe_id'=> '1wwweqe32',
            'user_name' => 'maurico',
            'recipe_name'=> 'coffeemix',
            'recipe_description'=> 'tastes like... coffee?',
            'coffee_bean'=> 'Liberica',
            'coffee_volume'=> 6,
            'coffee_roast'=> 'Dark Roast',
            'coffee_prep_time'=> '00:10:00',
        ]);
        DB::table('recipes')->insert([
            'recipe_id'=> '1awweqe32',
            'user_name' => 'apyr',
            'recipe_name'=> 'coffeemix dew',
            'recipe_description'=> 'tastes like... coffee? but sweeter :^)',
            'coffee_bean'=> 'Ribero',
            'coffee_volume'=> 3,
            'coffee_roast'=> 'Light Roast',
            'coffee_prep_time'=> '00:20:00',
        ]);
        DB::table('recipes')->insert([
            'recipe_id'=> '1ewweqe32',
            'user_name' => 'apyr',
            'recipe_name'=> 'coffeemix dew mk2',
            'recipe_description'=> 'tastes like... coffee? but with milk ^^)',
            'coffee_bean'=> 'Liberica',
            'coffee_volume'=> 3,
            'coffee_roast'=> 'Dark Roast',
            'coffee_prep_time'=> '00:05:00',
        ]);
    }
}
