package com.jwcnetworks.bsyoo.jwc.user.terms;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jwcnetworks.bsyoo.jwc.R;

public class TermsTabFragment2 extends TermsFragment {

    private View view = null;
    private TextView tv_termsinfo2;

    public TermsTabFragment2(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_terms_tab_fragment2, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tv_termsinfo2 = (TextView) view.findViewById(R.id.tv_termsinfo2);
        tv_termsinfo2.setText("개인정보의 수집목적 및 이용목적\n" +"\n" +
                "① JWC네트웍스는 회원님께 최대한으로 최적화되고 맞춤화된 서비스를 제공하기 위하여 다음과 같은 목적으로 개인정보를 수집하고 있습니다.\n" +
                "- 성명, 아이디, 비밀번호 : 회원제 서비스 이용에 따른 본인 식별 절차에 이용\n" +
                "- 이메일주소, 이메일 수신여부, 전화번호 : 고지사항 전달, 본인 의사 확인, 불만 처리 등 원활한 의사소통 경로의 확보, 새로운 서비스/신상품이나 이벤트 정보의 안내\n" +
                "- 주소, 전화번호 : 경품과 쇼핑 물품 배송에 대한 정확한 배송지의 확보\n" +
                "- 비밀번호 힌트용 질문과 답변 : 비밀번호를 잊은 경우의 신속한 처리를 위한 내용\n" +
                "- 그 외 선택항목 : 개인맞춤 서비스를 제공하기 위한 자료 ② 단, 이용자의 기본적 인권 침해의 우려가 있는 민감한 개인정보(인종 및 민족, 사상 및 신조, 출신지 및 본적지, 정치적 성향 및 범죄기록, 건강상태 및 성생활 등)는 수집하지 않습니다.\n" +
                "\n" +
                "\n" +
                "개인정보의 수집범위\n" +"\n" +
                "JWC네트웍스는 별도의 회원가입 절차 없이 대부분의 컨텐츠에 자유롭게 접근할 수 있습니다. JWC네트웍스의 회원제 서비스를 이용하시고자 할 경우 다음의 정보를 입력해주셔야 하며 선택항목을 입력하시지 않았다 하여 서비스 이용에 제한은 없습니다.\n" +
                "1) 회원 가입시 수집하는 개인정보의 범위\n" +
                "- 필수항목 : 희망 ID, 비밀번호, 비밀번호 힌트용 질문과 답변, 성명, 주소, 전화번호, 이메일주소, 이메일 수신 여부\n" +
                "- 선택항목 : 회사주소, 회사전화번호, 생년월일, 결혼여부, 결혼기념일, 직업, 월평균소득, 최종학력, 자녀수, 차량정보\n" +
                "\n" +
                "\n" +
                "개인정보의 보유기간 및 이용기\n" +"\n" +
                "① 귀하의 개인정보는 다음과 같이 개인정보의 수집목적 또는 제공받은 목적이 달성되면 파기됩니다. 단, 상법 등 관련법령의 규정에 의하여 다음과 같이 거래 관련 권리 의무 관계의 확인 등을 이유로 일정기간 보유하여야 할 필요가 있을 경우에는 일정기간 보유합니다.\n" +
                "- 회원가입정보의 경우, 회원가입을 탈퇴하거나 회원에서 제명된 경우 등 일정한 사전에 보유목적, 기간 및 보유하는 개인정보 항목을 명시하여 동의를 구합니다.\n" +
                "- 계약 또는 청약철회 등에 관한 기록 : 5년\n" +
                "- 소비자의 불만 또는 분쟁처리에 관한 기록 : 3년\n" +
                "② 귀하의 동의를 받아 보유하고 있는 거래정보 등을 귀하께서 열람을 요구하는 경우 JWC네트웍스는 지체없이 그 열람,확인 할 수 있도록 조치합니다.\n");
    }
}
