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
        company.setTeam_name("영업부");
        company.setTeam_Title("'대충 할 바에 안하는게 낫다'");
        company.setTv_Company("JWC 영업팀은 영상보안 솔루션의 전문가로 구성된 팀으로 최적의 솔루션을 제공해 드릴 것을 약속 드리며, 매출과 목표달성에 치우치지 않고 언제나 고객의 소리에 귀 기울입니다.");
        company.setImg_Company(R.drawable.company_1);
        company.setImg_Work(R.drawable.img_work1);
        companylist.add(company);

        company = new ModelCompany();
        company.setTeam_name("온라인 사업부");
        company.setTeam_Title("'99%의 고객만족은 불충분하다'");
        company.setTv_Company("JWC 온라인팀은 웹마케팅, 디자인 전문가로 구성된 팀으로 웹을 통한 최적의 구매환경을 제공해 드릴 것을 약속 드리며, 100%의 고객만족이 될때까지 최선을 다하겠습니다.");
        company.setImg_Company(R.drawable.company_1);
        company.setImg_Work(R.drawable.img_work2);
        companylist.add(company);

        company = new ModelCompany();
        company.setTeam_name("기술지원부");
        company.setTeam_Title("'품질로 회사를 바꾸는 사람들'");
        company.setTv_Company("JWC하면 품질과 서비스가 먼저 떠오르는 회사가 되도록 최선의 노력을 기울이며, 고객만족서비스와 1%미만의 불량율을 향해 나아가겠습니다.");
        company.setImg_Company(R.drawable.company_1);
        company.setImg_Work(R.drawable.img_work3);
        companylist.add(company);

        company = new ModelCompany();
        company.setTeam_name("생산부");
        company.setTeam_Title("'최고의 품질로 고객에게 한걸음 다가가겠다'");
        company.setTv_Company("JWC 생산팀은 카메라 및 DVR 제조에 최적화된 팀으로 최고의 제품 생산을 약속드리며, 품질관리를 통해 불량률 제로에 도전하겠습니다.");
        company.setImg_Company(R.drawable.company_1);
        company.setImg_Work(R.drawable.img_work4);
        companylist.add(company);

        company = new ModelCompany();
        company.setTeam_name("자재부");
        company.setTeam_Title("'노련의 변화와 젊은 도전, 유쾌한 물류'");
        company.setTv_Company("JWC 물류팀은 오랜 경력과 지식으로 유통과 물류 전문 담당자로 구성되어 힘든 유통과 어려운 관리 보다는 기발한 창의력과 새로운 도전으로 전문화된 물류 프로세스를 만들어 점점 더 발전하는 물류팀 입니다.");
        company.setImg_Company(R.drawable.company_1);
        company.setImg_Work(R.drawable.img_work5);
        companylist.add(company);
    }
}