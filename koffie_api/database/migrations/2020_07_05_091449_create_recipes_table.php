<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateRecipesTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('recipes', function (Blueprint $table) {
            $table->string('recipe_id')->unique();
            $table->string('user_name');

            $table->string('recipe_name');
            $table->string('recipe_description')->nullable();
            
            $table->string('coffee_bean');
            $table->double('coffee_volume');

            $table->string('coffee_roast')->nullable(); 
            $table->time('coffee_prep_time');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('recipes');
    }
}
