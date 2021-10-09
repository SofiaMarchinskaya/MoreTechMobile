package com.sofiamarchinskaya.moretechmobile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.sofiamarchinskaya.moretechmobile.models.Company;

import java.util.List;

public class AboutCompanyFragment extends Fragment {
    private final int img_id;
    private final String title;
    private final String aboutCompany;
    private final List<Company> relativeCompanies;
    AboutCompanyFragment(int img_id, String title, String aboutCompany, List<Company> relativeCompanies){
        this.img_id = img_id;
        this.title = title;
        this.aboutCompany = aboutCompany;
        this.relativeCompanies = relativeCompanies;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.about_company_fragment, container, false);
        ((ImageView)result.findViewById(R.id.company_image)).setImageResource(img_id);
        ((TextView)result.findViewById(R.id.about_company)).setText(aboutCompany);
        ((TextView)result.findViewById(R.id.company_name)).setText(title);
        LinearLayout linearLayout = result.findViewById(R.id.linear_companies);
        LinearLayout.LayoutParams linLayoutParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linLayoutParam.setMargins(0,0,16,16);
        final View view = getLayoutInflater().inflate(R.layout.company_text_image, null);
        ImageView img;
        TextView textView;
        for (int i = 0; i <relativeCompanies.size(); i++) {
            img = new ImageView(getContext());
            img.setBackgroundResource(relativeCompanies.get(i).getImageResources());
            textView = new TextView(getContext());
            textView.setText(relativeCompanies.get(i).getTitle());
            textView.setTextSize(17);
            textView.setTextColor(getResources().getColor(R.color.blueBlack));
            LinearLayout layout = new LinearLayout(view.getContext());
            layout.addView(img,linLayoutParam);
            layout.addView(textView,linLayoutParam);
            linearLayout.addView(layout,linLayoutParam);
        }
        return result;
    }
}
