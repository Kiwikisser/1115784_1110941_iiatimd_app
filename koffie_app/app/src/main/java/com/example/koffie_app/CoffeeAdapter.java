package com.example.koffie_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CoffeeAdapter extends RecyclerView.Adapter<CoffeeAdapter.CoffeeHolder> {
    private List<Coffee> coffee = new ArrayList<>();
    private Context ctx;
    private String packageName;
//    private Bundle bundleForCoffeeSummary;
    private CoffeeHolder coffeeHolder;
    private RecyclerView recyclerView;

    public CoffeeAdapter(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }
//    private final View.OnClickListener mOnClickListener = new OnClickListener();

    @NonNull
    @Override
    public CoffeeHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.coffee_overview_card, parent, false);
        final Context context = parent.getContext();
        this.ctx = context;
        this.packageName = context.getPackageName();

        coffeeHolder = new CoffeeHolder(itemView);

        itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(final View view) {
                int itemPosition = recyclerView.getChildLayoutPosition(view);
                Coffee item = coffee.get(itemPosition);
                Toast.makeText(ctx, item.getName(), Toast.LENGTH_LONG).show();

                String name = item.getName();
                String ingredients = item.getDescription(); // don't ask I don't know ok
                String description = item.getIngredients(); // don't ask I don't know ok
                String coffee_bean = item.getBeans();
                int servings = item.getServings();
                int prep_time = item.getPrepTime();
                String image = item.getImage();

                Bundle bundleForCoffeeSummary = new Bundle();
                bundleForCoffeeSummary.putString("name", name);
                bundleForCoffeeSummary.putString("description", description);
                bundleForCoffeeSummary.putString("ingredients", ingredients);
                bundleForCoffeeSummary.putString("coffee_bean", coffee_bean);
                bundleForCoffeeSummary.putInt("servings", servings);
                bundleForCoffeeSummary.putInt("prep_time", prep_time);
                bundleForCoffeeSummary.putString("image", image);

                Log.d( "onClick: ", String.valueOf(bundleForCoffeeSummary.get("description")));

                Log.d("onCreate: ", item.getDescription());

                Intent intent = new Intent(context, CoffeeSummaryActivity.class);
                intent.putExtras(bundleForCoffeeSummary);
                context.startActivity(intent);
            }
        });
        return coffeeHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CoffeeHolder holder, int position) {
        Coffee currentCoffee = coffee.get(position);

        String icon = "dummy_coffee";
//        int resID = getReources

        //code styling
        holder.textViewTitle.setTypeface(ResourcesCompat.getFont(ctx, R.font.rock_salt_regular));

        //put assigners
        holder.textViewTitle.setText(currentCoffee.getName());
        holder.textViewDescription.setText(currentCoffee.getDescription());
//        holder.imageView.setImageResource(R.drawable.dummy_coffee);      // TODO: 05/07/2020 retrieve images
        holder.imageView.setImageResource(retrieveDrawable(currentCoffee.getImage(), packageName));
        Log.d("loaded image res: ", String.valueOf(retrieveDrawable(currentCoffee.getImage(), packageName)));
//        holder.textViewPriority.setText(String.valueOf(currentCoffee.getPriority()));
    }

    @Override
    public int getItemCount() {
        return coffee.size();
    }

    public int retrieveDrawable(String imageName, String packageName){
        Log.d("retrieveDraw img: ", imageName);
        Log.d("retrieveDraw pkg: ", packageName);
        int resID = ctx.getResources().getIdentifier(imageName, "drawable", packageName);
        return resID;
    }

    public void setCoffee(List<Coffee> coffee){
        this.coffee = coffee;
        notifyDataSetChanged();
    }

    class CoffeeHolder extends RecyclerView.ViewHolder{
        private TextView textViewTitle;
        private TextView textViewDescription;
        private ImageView imageView;
//        private TextView textViewPriority;

        public CoffeeHolder(@NonNull View itemView) {
            super(itemView);
//            textViewTitle = itemView.findViewById(R.id.text_vew_title);
//            textViewDescription = itemView.findViewById(R.id.text_view_description);
//            textViewPriority = itemView.findViewById(R.id.text_view_priority);
            textViewTitle = itemView.findViewById(R.id.coffeecard_textView);
            textViewDescription= itemView.findViewById(R.id.coffeecard_textView_desc);
            imageView = itemView.findViewById(R.id.coffeecard_imageView);
        }
    }
}

















//class CoffeeOverviewAdapter extends RecyclerView.Adapter<CoffeeOverviewAdapter.CoffeeOverviewViewHolder> {
//
////    private Coffee[] coffee;
////    private Context context;
//
//    private List<Coffee> coffee = new ArrayList<>();
//
////    public CoffeeOverviewAdapter(Coffee[] coffees, Context context){
////        this.coffee = coffees;
////        this.context = context;
////    }
//
//    public CoffeeOverviewAdapter(Context context){
////        this.coffee = coffees;
//        this.context = context;
//    }
//
//    // internal class
//    public static class CoffeeOverviewViewHolder extends RecyclerView.ViewHolder{
//        public TextView textViewTitle;
//        public TextView textViewDesc;
//        public ImageView imageView;
//
//        public CoffeeOverviewViewHolder(View v, Context context){
//            super(v);
//            textViewTitle = v.findViewById(R.id.coffeecard_textView);
//            textViewTitle.setTypeface(ResourcesCompat.getFont(context, R.font.rock_salt_regular));
//            textViewDesc= v.findViewById(R.id.coffeecard_textView_desc);
////            textViewDesc.setTypeface(ResourcesCompat.getFont(context, R.font.rock_salt_regular));
////            textView.setTextColor(Color.parseColor("#FFFFFF"));
//            imageView = v.findViewById(R.id.coffeecard_imageView);
//
////            textView.setTypeface(ResourcesCompat.getFont(context, R.font.RockSalt_Regular));
//        }
//    }
//
//    @NonNull
//    @Override
//    public CoffeeOverviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.coffee_overview_card,parent,
//                false);
//        v.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context,
//                        CoffeeIntroduction.class);
//                context.startActivity(intent);
//
//            }
//        });
//        CoffeeOverviewViewHolder coffeeOverviewViewHolder = new CoffeeOverviewViewHolder(v, context);
//        return coffeeOverviewViewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull CoffeeOverviewViewHolder holder, int position) {
//        holder.textViewTitle.setText(coffee[position].getName());
////        holder.textViewTitle.setTextColor(Color.parseColor(coffees[position].getColorCode()));
//        holder.textViewDesc.setText(coffee[position].getDescription());
//        holder.imageView.setImageResource(R.drawable.dummy_coffee);      // TODO: 05/07/2020 retrieve images
//        // from room db
//    }
//
//    @Override
//    public int getItemCount() {
//        return coffee.length;
//    }
//
//    public void setCoffees(Coffee[] nCoffee){
//        coffee = nCoffee;
//        notifyDataSetChanged();
//    }
//
//}
