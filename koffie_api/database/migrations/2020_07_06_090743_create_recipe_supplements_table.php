<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateRecipeSupplementsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('recipe_supplements', function (Blueprint $table) {
            $table->string('recipe_supplement_id');
            $table->string('user_name');
            $table->string('supplement_name');
            $table->double('supplement_amount');
            $table->string('supplement_amount_format')->default('grams');

            $table->foreign('recipe_supplement_id')->references('recipe_id')->on('recipes');
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
        Schema::dropIfExists('recipe_supplements');
    }
}
