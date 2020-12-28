package com.android.testdemo.function;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.android.testdemo.R;
import com.android.testdemo.utils.LogUtil;
import com.android.testdemo.utils.ScreenUtil;

/**
 * Title: Region----区域
 * Description:
 *
 * Region: 译为“区域”，是一块任意形状的封闭图形,
 *         很多时候，我们会利用Region来构造一个图形
 *
 * Created by pei
 * Date: 2018/12/26
 */
public class RegionView extends View{

    private Context mContext;
    private int mWidth;
    private int mHeight;

    public RegionView(Context context) {
        super(context);
        this.mContext=context;
    }

    public RegionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext=context;
    }

    public RegionView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //初始化
        init(canvas);
        //直接构建Region
        directbuildRegion(canvas);
        //间接构建Region
        inDirectBuildRegion(canvas);
        //Region的setPath方法求path和region的交集
        pathAndRegionIntersection(canvas);
        //区域取并集
        rectAndRectIntersection(canvas);

        //区域操作
        controOne(canvas);//交集
        controTwo(canvas);//补集
        controThree(canvas);//替换
        controFour(canvas);//反转补集
        controFive(canvas);//并集
        controSix(canvas);//异并集
    }

    private void init(Canvas canvas){
        //获取屏幕尺寸
        mWidth= ScreenUtil.getWidth();
        mHeight=ScreenUtil.getHeight();
        //屏幕尺寸==width=1080   height=1812
        LogUtil.i("====屏幕尺寸==width="+mWidth+"   height="+mHeight);
        //设置画布背景
        canvas.drawColor(Color.WHITE);
    }

    /**直接构建Region**/
    private void directbuildRegion(Canvas canvas){
        //直接构建region的方法：
        //1. public Region(Region region); //复制一个Region的区域
        //2. Region(Rect r);//通过 Rect 构建Region
        //3. Region(int left, int top, int right, int bottom);//通过坐标点构建region

        //设置画笔
        Paint paint=new Paint();
        paint.setColor(getRidColor(R.color.color_0a900a));
        paint.setStyle(Paint.Style.FILL);
//        paint.setStrokeWidth(5f);//无描边，设置setStrokeWidth无效

        //构建矩形
        Rect rect=new Rect();
        rect.set(340,50,740,250);
        Region region=new Region(rect);
        //绘制Region
        drawRegion(canvas,region,paint);
    }

    /**间接构建region**/
    private void inDirectBuildRegion(Canvas canvas){
        Region region=new Region();

//        region.setEmpty();
//        region.set(Region region);
//        region.set(Rect rect);
//        region.set(int left,int top,int right,int bottom);
//        region.setPath(Path path,Region region);
    }

    /**Region的setPath方法求path和region的交集**/
    private void pathAndRegionIntersection(Canvas canvas){
        //设置paint
        Paint paint=new Paint();
        paint.setColor(getRidColor(R.color.color_f5cc1d));
        paint.setStyle(Paint.Style.FILL);
        //构造椭圆路径
        Path path=new Path();
        //构建椭圆path
        RectF rectF=new RectF(100,300,980,500);
        path.addOval(rectF,Path.Direction.CCW);//Path.Direction.CCW:逆时针;Path.Direction.CW:顺时针
        //构建Region
        Region region=new Region();
        region.set(540,300,980,500);
        //取path和region的交集
        Region rgn=new Region();
        rgn.setPath(path,region);
        //绘制区域
        drawRegion(canvas,rgn,paint);
    }

    /**区域取并集**/
    private void rectAndRectIntersection(Canvas canvas){
        //设置画笔
        Paint paint=new Paint();
        paint.setColor(getRidColor(R.color.color_12aef7));
        paint.setStyle(Paint.Style.FILL);
        //设置区域
        Region region=new Region(540,550,980,650);
        region.union(new Rect(490,600,590,700));
        //绘制区域
        drawRegion(canvas,region,paint);
    }

//=========================区域操作=========================================
    /**交集**/
    private void controOne(Canvas canvas){
        Paint paint=new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        //绘制矩形轨迹
        Rect rOne=new Rect(120,750,220,1050);
        Rect rTwo=new Rect(20,850,320,950);
        canvas.drawRect(rOne,paint);
        canvas.drawRect(rTwo,paint);

        Region regionOne=new Region(rOne);
        Region regionTwo=new Region(rTwo);
        regionTwo.op(regionOne,Region.Op.INTERSECT);//交集
        paint.setStyle(Paint.Style.FILL);
        drawRegion(canvas,regionTwo,paint);
    }

    /**补集**/
    private void controTwo(Canvas canvas){
        //设置画笔
        Paint paint=new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);

        //绘制矩形轨迹
        Rect rectOne=new Rect(440,750,540,1050);
        Rect rectTwo=new Rect(340,850,640,950);
        canvas.drawRect(rectOne,paint);
        canvas.drawRect(rectTwo,paint);
        //绘制区域
        paint.setStyle(Paint.Style.FILL);
        Region regionOne=new Region(rectOne);
        Region regionTwo=new Region(rectTwo);
        regionTwo.op(regionOne,Region.Op.DIFFERENCE);
        drawRegion(canvas,regionTwo,paint);
    }

    /**替换**/
    private void controThree(Canvas canvas){
        //设置画笔
        Paint paint=new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        //设置矩形轨迹
        Rect rectOne=new Rect(760,750,860,1050);
        Rect rectTwo=new Rect(660,850,960,950);
        canvas.drawRect(rectOne,paint);
        canvas.drawRect(rectTwo,paint);
        //绘制区域
        Region regionOne=new Region(rectOne);
        Region regionTwo=new Region(rectTwo);
        regionTwo.op(regionOne,Region.Op.REPLACE);
        paint.setStyle(Paint.Style.FILL);
        drawRegion(canvas,regionOne,paint);
    }

    /**反转补集**/
    private void controFour(Canvas canvas){
        //设置画笔
        Paint paint=new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        //绘制矩形轨迹
        Rect rectOne=new Rect(120,1100,220,1400);
        Rect rectTwo=new Rect(20,1200,320,1300);
        canvas.drawRect(rectOne,paint);
        canvas.drawRect(rectTwo,paint);
        //绘制区域
        Region regionOne=new Region(rectOne);
        Region regionTwo=new Region(rectTwo);
        paint.setStyle(Paint.Style.FILL);
        regionTwo.op(regionOne,Region.Op.REVERSE_DIFFERENCE);
        drawRegion(canvas,regionTwo,paint);
    }

    /**并集**/
    private void controFive(Canvas canvas){
        //设置画笔
        Paint paint=new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        //绘制矩形
        Rect rectOne=new Rect(440,1100,540,1400);
        Rect rectTwo=new Rect(340,1200,640,1300);
        canvas.drawRect(rectOne,paint);
        canvas.drawRect(rectTwo,paint);
        //绘制区域
        paint.setStyle(Paint.Style.FILL);
        Region regionOne=new Region(rectOne);
        Region regionTwo=new Region(rectTwo);
        regionTwo.op(regionOne,Region.Op.UNION);
        drawRegion(canvas,regionTwo,paint);
    }

    /**异并集**/
    private void controSix(Canvas canvas){
        //设置Paint
        Paint paint=new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        //绘制矩形
        Rect rectOne=new Rect(760,1100,860,1400);
        Rect rectTwo=new Rect(660,1200,960,1300);
        canvas.drawRect(rectOne,paint);
        canvas.drawRect(rectTwo,paint);
        //绘制区域
        paint.setStyle(Paint.Style.FILL);
        Region regionOne=new Region(rectOne);
        Region regionTwo=new Region(rectTwo);
        regionTwo.op(regionOne,Region.Op.XOR);
        drawRegion(canvas,regionTwo,paint);
    }

//===========================================================================

    /**绘制Region**/
    private void drawRegion(Canvas canvas,Region region,Paint paint){
        //Android还提供了一个RegionIterator来对Region中的所有矩阵进行迭代，
        // 可以使用该类，获得某个Region的所有矩阵
        //通过遍历region中的矩阵，并绘制出来，来绘制region
        RegionIterator iterator=new RegionIterator(region);
        Rect r=new Rect();
        while(iterator.next(r)){
            canvas.drawRect(r,paint);
        }
    }


    /**设置颜色**/
    private int getRidColor(int color){
        return ContextCompat.getColor(mContext,color);
    }



}
