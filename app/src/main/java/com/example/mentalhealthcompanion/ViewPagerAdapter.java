package com.example.mentalhealthcompanion;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolder> {

    private final Context context;

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    int[] image = {
            R.raw.onboard1_oga_developer,
            R.raw.onboard2_tech_brain,
            R.raw.onboard3_yoga
    };

    int[] heading = {
            R.string.onboard1_head,
            R.string.onboard2_head,
            R.string.onboard3_head
    };

    int[] description = {
            R.string.onboard1_description,
            R.string.onboard2_description,
            R.string.onboard3_description
    };

    int[] cta_btns = {
            R.string.onboard1_cta_btn,
            R.string.onboard2_cta_btn,
            R.string.onboard3_cta_btn
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.slider_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onboard1_img.setAnimation(image[position]);
        holder.onboard1_img.setRepeatCount(LottieDrawable.INFINITE); // Infinite loop
        holder.onboard1_img.playAnimation(); // Start the animation

        holder.onboard1heading.setText(heading[position]);
        holder.onboard1description.setText(description[position]);
        holder.onboard_cta_btn.setText(cta_btns[position]); // Set the CTA button text

        holder.onboard_cta_btn.setOnClickListener(v -> {
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return heading.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LottieAnimationView onboard1_img;
        TextView onboard1heading;
        TextView onboard1description;
        AppCompatButton onboard_cta_btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            onboard1_img = itemView.findViewById(R.id.onboard1_img);
            onboard1heading = itemView.findViewById(R.id.onboard1heading);
            onboard1description = itemView.findViewById(R.id.onboard1description);
            onboard_cta_btn = itemView.findViewById(R.id.onboard_cta_btn1);
        }
    }
}
