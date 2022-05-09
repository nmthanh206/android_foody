package hcmute.spkt.foody_g7;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {
    List<FoodItem> foods;
    LinearLayout listFood;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        foods = new ArrayList<FoodItem>();

        View view = inflater.inflate(R.layout.fragment_home, container, false);


        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listFood = (LinearLayout) view.findViewById(R.id.listFood);
        String[] arrCategory = {"All", "Rice", "Drink", "Healthy","Fast","Snacks","Hotpot","Foreign"};
        FoodItem foodItem;
        for (int i = 0; i < 30; i++) {
            foods.add(new FoodItem(R.drawable.dishes_1, "Quan "+String.valueOf(i+1), (double)Util.getRandomNumber(50,500), arrCategory[Util.getRandomNumber(1,arrCategory.length-1)]));
        }

        State.foods = new ArrayList<FoodItem>(foods);;
        LoadData();


        View viewAll = view.findViewById(R.id.all);
        View viewRice = view.findViewById(R.id.rice);
        View viewDrinks = view.findViewById(R.id.drinks);
        View viewHealthy = view.findViewById(R.id.healthy);
        View viewFast = view.findViewById(R.id.fast);
        View viewSnacks = view.findViewById(R.id.snacks);
        View viewHotpot = view.findViewById(R.id.hotpot);
        View viewForeign = view.findViewById(R.id.foreign);
        viewAll.setOnClickListener(this);
        viewRice.setOnClickListener(this);
        viewDrinks.setOnClickListener(this);
        viewHealthy.setOnClickListener(this);
        viewFast.setOnClickListener(this);
        viewSnacks.setOnClickListener(this);
        viewHotpot.setOnClickListener(this);
        viewForeign.setOnClickListener(this);

//        listFood.removeAllViews();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {

        listFood.removeAllViews();
        foods = new ArrayList<FoodItem>(State.foods);;
        switch (v.getId()) {
            case R.id.all: {
//                foods = new ArrayList<FoodItem>(State.foods);;

                LoadData();
                break;
            }
            case R.id.rice: {
                foods.removeIf(p -> !p.getCategory().equals("Rice"));
                break;
            }
            case R.id.drinks: {
                foods.removeIf(p -> !p.getCategory().equals("Drink"));
                break;
            }
            case R.id.healthy: {
                foods.removeIf(p -> !p.getCategory().equals("Healthy"));
                break;
            }
            case R.id.fast: {
                foods.removeIf(p -> !p.getCategory().equals("Fast"));
                break;
            }
            case R.id.snacks: {
                foods.removeIf(p -> !p.getCategory().equals("Snacks"));
                break;
            }
            case R.id.hotpot: {
                foods.removeIf(p -> !p.getCategory().equals("Hotpot"));
                break;
            }
            case R.id.foreign: {
                foods.removeIf(p -> !p.getCategory().equals("Foreign"));
                break;
            }
        }
        LoadData();
//
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void LoadData() {
        foods.forEach((element) -> {
            listFood.addView(foodItem(element));
        });
    }

    private LinearLayout foodItem(FoodItem item) {
        LinearLayout dishContainer = new LinearLayout(getActivity());
//        dishContainer.setId(R.id.dishContainer);
        dishContainer.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.width = LinearLayout.LayoutParams.MATCH_PARENT;
        layout.height = LinearLayout.LayoutParams.WRAP_CONTENT;


        layout.bottomMargin = dpToPx(15);
        dishContainer.setLayoutParams(layout);

        ImageView imageView1 = new ImageView(getActivity());
        imageView1.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView1.setImageResource(R.drawable.dishes_1);
        LinearLayout.LayoutParams layout_740 = new LinearLayout.LayoutParams(dpToPx(120), dpToPx(100));
        layout_740.width = dpToPx(120);
        layout_740.height = dpToPx(100);
        imageView1.setLayoutParams(layout_740);
        dishContainer.addView(imageView1);

        LinearLayout dishInfoContainer = new LinearLayout(getActivity());
//        dishInfoContainer.setId(R.id.dishInfoContainer);
        dishInfoContainer.setGravity(Gravity.CENTER_VERTICAL);
        dishInfoContainer.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layout2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, dpToPx(100));
        layout2.width = LinearLayout.LayoutParams.WRAP_CONTENT;
        layout2.height = dpToPx(100);
        layout2.leftMargin = dpToPx(10);
        dishInfoContainer.setLayoutParams(layout2);

        TextView textView1 = new TextView(getActivity());
        textView1.setText(item.getName());
        textView1.setTypeface(null, Typeface.BOLD);
        textView1.setTextSize(Util.convertDpToPixel(6, getContext()));
        LinearLayout.LayoutParams layout_676 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout_676.width = LinearLayout.LayoutParams.WRAP_CONTENT;
        layout_676.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        textView1.setLayoutParams(layout_676);
        dishInfoContainer.addView(textView1);

        TextView textView2 = new TextView(getActivity());
        textView2.setText("Avg. " + item.getPrice() + "k");
        textView2.setTextColor(getResources().getColor(R.color.blue));
        textView2.setTextSize(Util.convertDpToPixel(5, getContext()));
        LinearLayout.LayoutParams layout_545 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout_545.width = LinearLayout.LayoutParams.WRAP_CONTENT;
        layout_545.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        textView2.setLayoutParams(layout_545);
        dishInfoContainer.addView(textView2);

        TextView textView3 = new TextView(getActivity());
        textView3.setText(item.getCategory());
        textView3.setTextSize(Util.convertDpToPixel(4, getContext()));
        LinearLayout.LayoutParams layout_675 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout_675.width = LinearLayout.LayoutParams.WRAP_CONTENT;
        layout_675.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        textView3.setLayoutParams(layout_675);
        dishInfoContainer.addView(textView3);
        dishContainer.addView(dishInfoContainer);
        return dishContainer;
    }

    public int dpToPx(int dp) {
        return (int) (dp * getContext().getResources().getDisplayMetrics().density);
    }

    public int pxToDp(int px) {
        return (int) (px / getContext().getResources().getDisplayMetrics().density);
    }

}