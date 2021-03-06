package com.example.accountbook_uiux;

import static com.example.accountbook_uiux.MainActivity.dbHelper;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class StatsViewFragment extends Fragment
{

    private View view_stats;
    MainActivity mainActivity = new MainActivity();
    TextView tv_foodsum,tv_traffic,tv_leisure,tv_mart,tv_things,tv_fashion,tv_learn,tv_commu,tv_health,tv_fee,tv_etcsum;
    Button btn_toBar, btn_toPie;

    PieChart pieChart;
    BarChart barChart;
    long now = System.currentTimeMillis();
    Date date = new Date(now);
    SimpleDateFormat df = new SimpleDateFormat("MM");
    String getTime = df.format(date);
    Calendar cal1 = Calendar.getInstance();
    Calendar cal2 = Calendar.getInstance();
    Calendar cal3 = Calendar.getInstance();
    Calendar cal4 = Calendar.getInstance();
    Calendar cal5 = Calendar.getInstance();
    Calendar cal6 = Calendar.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view_stats = inflater.inflate(R.layout.frame_stats, container, false);

// ??????, ?????????,  ?????????,??????/??????,??????,?????????, ??????,??????/??????, ??????/??????, ??????

        tv_foodsum = (TextView) view_stats.findViewById(R.id.tv_foodsum);
        tv_traffic = (TextView) view_stats.findViewById(R.id.tv_traffic);
        tv_leisure = (TextView) view_stats.findViewById(R.id.tv_leisure);
        tv_mart = (TextView) view_stats.findViewById(R.id.tv_mart);
        tv_things = (TextView) view_stats.findViewById(R.id.tv_things);
        tv_fashion = (TextView) view_stats.findViewById(R.id.tv_fashion);
        tv_learn = (TextView) view_stats.findViewById(R.id.tv_learn);
        tv_commu = (TextView) view_stats.findViewById(R.id.tv_commu);
        tv_health = (TextView) view_stats.findViewById(R.id.tv_health);
        tv_fee = (TextView) view_stats.findViewById(R.id.tv_fee);
        tv_etcsum = (TextView) view_stats.findViewById(R.id.tv_etcsum);


        tv_foodsum.setText("?????? : "+dbHelper.getCategory("??????","??????")+"???");
        tv_traffic.setText("??????/?????? : "+dbHelper.getCategory("??????/??????","??????")+"???");
        tv_leisure.setText("???????????? : "+dbHelper.getCategory("????????????","??????")+"???");
        tv_mart.setText("??????/????????? : "+dbHelper.getCategory("??????/?????????","??????")+"???");
        tv_things.setText("????????? : "+dbHelper.getCategory("?????????","??????")+"???");
        tv_fashion.setText("??????/?????? : "+dbHelper.getCategory("??????/??????","??????")+"???");
        tv_learn.setText("?????? : "+dbHelper.getCategory("??????","??????")+"???");
        tv_commu.setText("??????/?????? : "+dbHelper.getCategory("??????/??????","??????")+"???");
        tv_health.setText("??????/?????? : "+dbHelper.getCategory("??????/??????","??????")+"???");
        tv_fee.setText("?????????/?????? : "+dbHelper.getCategory("?????????/??????","??????")+"???");
        tv_etcsum.setText("?????? : "+dbHelper.getCategory("??????","??????")+"???");

        cal1.setTime(new Date());
        cal2.setTime(new Date());
        cal3.setTime(new Date());
        cal4.setTime(new Date());
        cal5.setTime(new Date());
        cal6.setTime(new Date());

        cal1.add(Calendar.MONTH, -4);
        cal2.add(Calendar.MONTH, -3);
        cal3.add(Calendar.MONTH, -2);
        cal4.add(Calendar.MONTH, -1);
        // cal5 = current
        cal6.add(Calendar.MONTH, 1);


        Button btn_toBar = (Button) view_stats.findViewById(R.id.btn_toBar);
        Button btn_toPie = (Button) view_stats.findViewById(R.id.btn_toPie);

        btn_toBar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                pieChart.setVisibility(View.INVISIBLE);
                tv_traffic.setVisibility(View.INVISIBLE);
                tv_things.setVisibility(View.INVISIBLE);
                tv_mart.setVisibility(View.INVISIBLE);
                tv_foodsum.setVisibility(View.INVISIBLE);
                tv_leisure.setVisibility(View.INVISIBLE);
                tv_learn.setVisibility(View.INVISIBLE);
                tv_health.setVisibility(View.INVISIBLE);
                tv_fee.setVisibility(View.INVISIBLE);
                tv_fashion.setVisibility(View.INVISIBLE);
                tv_etcsum.setVisibility(View.INVISIBLE);
                tv_commu.setVisibility(View.INVISIBLE);
                btn_toBar.setVisibility(View.VISIBLE);
                btn_toPie.setVisibility(View.VISIBLE);
                barChart.setVisibility(View.VISIBLE);
            }
        });

        btn_toPie.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                barChart.setVisibility(View.INVISIBLE);
                tv_traffic.setVisibility(View.VISIBLE);
                tv_things.setVisibility(View.VISIBLE);
                tv_mart.setVisibility(View.VISIBLE);
                tv_foodsum.setVisibility(View.VISIBLE);
                tv_leisure.setVisibility(View.VISIBLE);
                tv_learn.setVisibility(View.VISIBLE);
                tv_health.setVisibility(View.VISIBLE);
                tv_fee.setVisibility(View.VISIBLE);
                tv_fashion.setVisibility(View.VISIBLE);
                tv_etcsum.setVisibility(View.VISIBLE);
                tv_commu.setVisibility(View.VISIBLE);
                btn_toBar.setVisibility(View.VISIBLE);
                btn_toPie.setVisibility(View.VISIBLE);

                pieChart.setVisibility(View.VISIBLE);

            }
        });

        barChart = (BarChart) view_stats.findViewById(R.id.barchart);






        pieChart = (PieChart) view_stats.findViewById(R.id.chart);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);
        pieChart.setEntryLabelColor(Color.BLACK);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleColor(Color.BLACK);
        pieChart.setTransparentCircleRadius(61f);



        ArrayList<PieEntry> yValues = new ArrayList<PieEntry>();

        if(dbHelper.getCategory("??????", "??????") > 0) // ?????? 0?????? ????????? add??????????????? 0?????? ????????? ????????? ?????? ??????
        {
            yValues.add(new PieEntry(dbHelper.getCategory("??????", "??????"),"??????"));
        }
        if(dbHelper.getCategory("??????/??????", "??????") > 0)
        {
            yValues.add(new PieEntry(dbHelper.getCategory("??????/??????", "??????"),"??????/??????"));
        }

        if(dbHelper.getCategory("????????????", "??????") > 0)
        {
            yValues.add(new PieEntry(dbHelper.getCategory("????????????", "??????"),"????????????"));
        }

        if(dbHelper.getCategory("??????/?????????", "??????") > 0)
        {
            yValues.add(new PieEntry(dbHelper.getCategory("??????/?????????", "??????"),"??????/?????????"));
        }

        if(dbHelper.getCategory("?????????", "??????") > 0)
        {
            yValues.add(new PieEntry(dbHelper.getCategory("?????????", "??????"),"?????????"));
        }
        if(dbHelper.getCategory("??????/??????", "??????") > 0)
        {
            yValues.add(new PieEntry(dbHelper.getCategory("??????/??????", "??????"),"??????/??????"));
        }
        if(dbHelper.getCategory("??????", "??????") > 0)
        {
            yValues.add(new PieEntry(dbHelper.getCategory("??????", "??????"),"??????"));
        }
        if(dbHelper.getCategory("??????/??????", "??????") > 0)
        {
            yValues.add(new PieEntry(dbHelper.getCategory("??????/??????", "??????"),"??????/??????"));
        }

        if(dbHelper.getCategory("??????/??????", "??????") > 0)
        {
            yValues.add(new PieEntry(dbHelper.getCategory("??????/??????", "??????"),"??????/??????"));
        }
        if(dbHelper.getCategory("?????????/??????", "??????") > 0)
        {
            yValues.add(new PieEntry(dbHelper.getCategory("?????????/??????", "??????"),"?????????/??????"));
        }
        if(dbHelper.getCategory("??????", "??????") > 0)
        {
            yValues.add(new PieEntry(dbHelper.getCategory("??????", "??????"), "??????"));
        }



        Description description2 = new Description();
        description2.setText(""); //??????
        description2.setTextSize(1);



        Description description = new Description();
        description.setText("??????????????? ??????"); //??????
        description.setTextSize(10);
        pieChart.setDescription(description2);
        //pieChart.animateY(1000, Easing.EasingOption.EaseInOutCubic); //???????????????


        PieDataSet dataSet = new PieDataSet(yValues,"????????????");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);

        PieData data = new PieData((dataSet));
        data.setValueTextSize(20f);
        data.setValueTextColor(Color.DKGRAY);

        pieChart.setData(data);

        BarDataSet barDataSet = new BarDataSet(data1(), "?????? 5?????? ????????????");
        BarData barData = new BarData();
        barData.addDataSet(barDataSet);
        barData.setBarWidth(0.35f);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);

        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawGridLines(false);
        leftAxis.setGranularity(1000f);

        barDataSet.setColor(Color.GREEN);


        YAxis rightAxis = barChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setEnabled(false);

        barChart.setData(barData);
        barChart.invalidate();
        barChart.setDescription(description2);
        barChart.setVisibility(View.INVISIBLE);
        barChart.setScaleEnabled(false);
        barChart.setPinchZoom(false);
        barDataSet.setColor(Color.parseColor("#7CD685"));

        Log.d("test","2021-"+df.format(cal6.getTime())+"-01");

        return view_stats;
    }

    private ArrayList<BarEntry> data1() // ?????? Month?????? 5????????? ??????
    {
        ArrayList<BarEntry> data_val = new ArrayList<>();
        data_val.add(new BarEntry(Integer.parseInt(df.format(cal1.getTime())), dbHelper.periodInquiry("2021-"+df.format(cal1.getTime())+"-01","2021-"+df.format(cal2.getTime())+"-01","??????")));
        data_val.add(new BarEntry(Integer.parseInt(df.format(cal2.getTime())), dbHelper.periodInquiry("2021-"+df.format(cal2.getTime())+"-01","2021-"+df.format(cal3.getTime())+"-01","??????")));
        data_val.add(new BarEntry(Integer.parseInt(df.format(cal3.getTime())), dbHelper.periodInquiry("2021-"+df.format(cal3.getTime())+"-01","2021-"+df.format(cal4.getTime())+"-01","??????")));
        data_val.add(new BarEntry(Integer.parseInt(df.format(cal4.getTime())), dbHelper.periodInquiry("2021-"+df.format(cal4.getTime())+"-01","2021-"+df.format(cal5.getTime())+"-01","??????")));
        data_val.add(new BarEntry(Integer.parseInt(df.format(cal5.getTime())), dbHelper.periodInquiry("2021-"+df.format(cal5.getTime())+"-01","2022-"+df.format(cal6.getTime())+"-01","??????")));


        return data_val;
    }
}
