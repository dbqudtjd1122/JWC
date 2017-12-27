package com.example.bsyoo.jwc.mainimage.Company;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.bsyoo.jwc.R;
import com.example.bsyoo.jwc.adapter.AdapterCompany;
import com.example.bsyoo.jwc.model.ModelCompany;

import java.util.ArrayList;
import java.util.List;

public class CompanyTabFragment2 extends CompanyFragment {

    private View view = null;
    private ListView listView;

    private List<ModelCompany> companylist;
    private AdapterCompany adapter;
    private ModelCompany company;



    public CompanyTabFragment2() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_company_tab_fragment2, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setdata();

        adapter = new AdapterCompany(getContext(), R.layout.listitem_company, R.id.tv_teamname, companylist);

        listView = (ListView) view.findViewById(R.id.list_company);

        listView.setAdapter(adapter);
    }

    private void setdata(){
        companylist = new ArrayList<>();

        company = new ModelCompany();
        company.setTeam_name("영업팀");
        company.setTeam_Title("'대충 할 바에 안하는게 낫다'");
        company.setTv_Company("JWC 영업팀은 영상보안 솔루션의 전문가로 구성된 팀으로 최적의 솔루션을 제공해 드릴 것을 약속 드리며, 매출과 목표달성에 치우치지 않고 언제나 고객의 소리에 귀 기울입니다.");
        company.setImg_Company(R.drawable.company_1);
        companylist.add(company);

        company = new ModelCompany();
        company.setTeam_name("온라인팀");
        company.setTeam_Title("'99%의 고객만족은 불충분하다.'");
        company.setTv_Company("JWC 온라인팀은 웹마케팅, 디자인 전문가로 구성된 팀으로 웹을 통한 최적의 구매환경을 제공해 드릴 것을 약속 드리며, 100%의 고객만족이 될때까지 최선을 다하겠습니다.");
        company.setImg_Company(R.drawable.company_1);
        companylist.add(company);

        company = new ModelCompany();
        company.setTeam_name("기술팀");
        company.setTv_Company(" - 원격지원\n - 펌웨어 테스트\n - A/S처리 서비스");
        companylist.add(company);

    }
}