package org.ditto.feature.buyanswer.edit.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.AMapGestureListener;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.MarkerOptions;

import org.ditto.feature.base.BaseFragment;
import org.ditto.feature.buyanswer.R;
import org.ditto.feature.buyanswer.R2;
import org.ditto.feature.buyanswer.di.BuyanswerViewModelFactory;
import org.ditto.feature.buyanswer.edit.BuyanswerEditViewModel;
import org.ditto.lib.dbroom.vo.VoGeofence;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A fragment representing a listCommandsBy of Items.
 * <p/>
 */
public class FragmentEditstepGeofence extends BaseFragment {


    @Inject
    BuyanswerViewModelFactory mBuyanswerViewModelFactory;
    private BuyanswerEditViewModel mBuyanswerEditViewModel;


    @BindView(R2.id.question_fence_desc)
    TextView questionFenceDesc;
    @BindView(R2.id.map)
    MapView mMapView;
    private AMap mAMap;
    private boolean showCurrentLocation = true;
    private boolean showChooseLocation = false;
    AMapLocationClient mLocationClient;
    AMapLocationClientOption mLocationOption = null;
    //声明mLocationOption对象
    private LatLng chooseLatLng = null;
    public AMapLocation myLocation;
    private static final Random RANDOM = new Random();
    private static final String CAROUSEL_DATA_KEY = "carousel_data_key";
    private boolean isChooseLocation = false;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FragmentEditstepGeofence() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBuyanswerEditViewModel = ViewModelProviders.of(this.getActivity(), mBuyanswerViewModelFactory).get(BuyanswerEditViewModel.class);

        mBuyanswerEditViewModel.getLiveBuyanswer().observe(this, buyanswer -> {
            if (buyanswer != null && buyanswer.voGeofence != null) {
                drawGeofence(buyanswer.voGeofence);
            }
        });
        mBuyanswerEditViewModel.getLiveSetGeofenceCommand().observe(this, setGeofenceCommand -> {
            if (setGeofenceCommand != null
                    && setGeofenceCommand.content != null
                    && setGeofenceCommand.content.setGeofence != null
                    && setGeofenceCommand.content.setGeofence.voGeofence != null) {
                drawGeofence(setGeofenceCommand.content.setGeofence.voGeofence);
            }
        });
        mBuyanswerEditViewModel.refresh();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = (View) inflater.inflate(R.layout.buyanswer_fragment_editstep_geofence, container, false);
        ButterKnife.bind(this, view);
        // Set the adapter


        mMapView.onCreate(savedInstanceState);
        startLocation();
        setUpMap();

        return view;
    }

    @OnClick(R2.id.map_mylocation)
    public void onMapMylocationButtonClicked() {
        if (myLocation != null) {
            mBuyanswerEditViewModel.set(VoGeofence
                    .builder()
                    .setCenterAddress(myLocation.getAddress())
                    .setLat(myLocation.getLatitude())
                    .setLon(myLocation.getLongitude())
                    .setRadius(computeRadius())
                    .build());
        } else {
            Snackbar.make(mMapView, "没有定位到你的位置，请检查GPS是否打开", Snackbar.LENGTH_LONG).show();
        }

        isChooseLocation = false;
    }

    @OnClick(R2.id.map_chooselocation)
    public void onMapChooselocationButtonClicked() {
        if (chooseLatLng == null) {
            Snackbar.make(questionFenceDesc, "请点击地图选点定位", Snackbar.LENGTH_LONG).show();
        } else {
            LatLng latLng = new LatLng(chooseLatLng.latitude, chooseLatLng.longitude);

            isChooseLocation = true;

            mBuyanswerEditViewModel.set(VoGeofence
                    .builder()
                    .setCenterAddress("选择的地方地址要计算")
                    .setLat(latLng.latitude)
                    .setLon(latLng.longitude)
                    .setRadius(computeRadius())
                    .build());
        }
    }

    @OnClick(R2.id.map_nolocation)
    public void onMapNolocationButtonClicked() {
        mBuyanswerEditViewModel.set(VoGeofence
                .builder()
                .setCenterAddress("全球人们")
                .setLat(0).setLon(0)
                .setRadius(-1)
                .build());
    }

    /**
     * * 初始化AMap对象
     */
    private void startLocation() {
        //初始化定位
        mLocationClient = new AMapLocationClient(this.getActivity().getApplicationContext());

        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);

        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //启动定位
        mLocationClient.startLocation();


    }

    private void setUpMap() {
        if (mAMap == null) {
            mAMap = mMapView.getMap();
            mAMap.setOnMapClickListener(mOnMapClickListener);
        }

        mAMap.animateCamera(CameraUpdateFactory.zoomTo(17));
        mAMap.getUiSettings().setScaleControlsEnabled(true);
        mAMap.getUiSettings().setZoomControlsEnabled(false);
        mAMap.setOnCameraChangeListener(new AMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {

            }

            @Override
            public void onCameraChangeFinish(CameraPosition cameraPosition) {
            }
        });
    }
//
//    /**
//     * 设置一些amap的属性
//     */
//    private void setUpMap() {
//        mAMap.setOnMapClickListener(this);
//        mAMap.setLocationSource(this);// 设置定位监听
//        mAMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
//        // 自定义系统定位蓝点
//        MyLocationStyle myLocationStyle = new MyLocationStyle();
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
//        // 自定义定位蓝点图标
//        myLocationStyle.myLocationIcon(
//                BitmapDescriptorFactory.fromResource(R.drawable.gps_point));
//        // 自定义精度范围的圆形边框颜色
//        myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));
//        // 自定义精度范围的圆形边框宽度
//        myLocationStyle.strokeWidth(0);
//        // 设置圆形的填充颜色
//        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));
//        // 将自定义的 myLocationStyle 对象添加到地图上
//        mAMap.setMyLocationStyle(myLocationStyle);
////        mAMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
//        // 设置定位的类型为定位模式 ，可以由定位、跟随或地图根据面向方向旋转几种
//        mAMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
//
//        questionFenceDesc.setVoText(String.format("设定长虹科技大厦为中心%d米方圆内", 123));
//
//
//    }

    @OnClick(R2.id.map_zoomin)
    public void onMapZoominButtonClicked() {
        mAMap.animateCamera(CameraUpdateFactory.zoomBy(0.2f), 500, new AMap.CancelableCallback() {
            @Override
            public void onFinish() {
                LatLng latLng = new LatLng(39.984059, 116.307771);
                if (!isChooseLocation && myLocation != null) {
                    latLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
                } else if (isChooseLocation && chooseLatLng != null) {
                    latLng = chooseLatLng;
                }

                int radius = computeRadius();
                drawGeofence(VoGeofence.builder().setCenterAddress("TODO:重新计算")
                        .setLat(latLng.latitude)
                        .setLon(latLng.longitude)
                        .setRadius(radius)
                        .build());
            }

            @Override
            public void onCancel() {

            }
        });

    }

    @OnClick(R2.id.map_zoomout)
    public void onMapZoomoutButtonClicked() {
        mAMap.animateCamera(CameraUpdateFactory.zoomBy(-0.2f), 500, new AMap.CancelableCallback() {
            @Override
            public void onFinish() {
                LatLng latLng = new LatLng(39.984059, 116.307771);
                if (!isChooseLocation && myLocation != null) {
                    latLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
                } else if (isChooseLocation && chooseLatLng != null) {
                    latLng = chooseLatLng;
                }
                int radius = computeRadius();
                drawGeofence(VoGeofence.builder().setCenterAddress("TODO:重新计算").setLat(latLng.latitude)
                        .setLon(latLng.longitude)
                        .setRadius(radius)
                        .build());
            }

            @Override
            public void onCancel() {

            }
        });

    }

    private void drawGeofence(VoGeofence voGeofence) {
        mAMap.clear();
        if (voGeofence == null || voGeofence.radius < 0) {
            questionFenceDesc.setText("欢迎全球各地人们来回答问题");
        } else {
            // 获取当前地图的缩放级别
            float mZoom = mAMap.getCameraPosition().zoom;

            LatLng latLng = new LatLng(voGeofence.lat, voGeofence.lon);
            BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.gps_point));
            mAMap.moveCamera(CameraUpdateFactory.changeLatLng(latLng));

            mAMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f).position(latLng).visible(true).icon(bitmapDescriptor));


            mAMap.addCircle(new CircleOptions().center(latLng).radius(voGeofence.radius).
                    fillColor(Color.argb(0, 1, 1, 1)));

            questionFenceDesc.setText(String.format("设定%s为中心方圆%d米内", voGeofence.centerAddress, voGeofence.radius));
        }
    }

    private int computeRadius() {
        LatLngBounds latLngBounds = mAMap.getProjection().getVisibleRegion().latLngBounds;
        LatLng northeast = latLngBounds.northeast;
        LatLng southwest = latLngBounds.southwest;
        LatLng northwest = new LatLng(northeast.latitude, southwest.longitude);

        float width = AMapUtils.calculateLineDistance(northeast, northwest);
        float height = AMapUtils.calculateLineDistance(northwest, southwest);

        return (int) width / 2;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }


    /**
     * 方法必须重写
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    public static Builder builder() {
        return new Builder();
    }

    AMap.OnMapClickListener mOnMapClickListener = new AMap.OnMapClickListener() {
        @Override
        public void onMapClick(LatLng latLng) {
            chooseLatLng = latLng;

            BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),
                    R.drawable.gps_point));
            mAMap.moveCamera(CameraUpdateFactory.changeLatLng(chooseLatLng));
            mAMap.clear();
            mAMap.addMarker(new MarkerOptions().title("选中位置").anchor(0.5f, 0.5f).position(latLng).visible(true).icon(bitmapDescriptor));

            drawGeofence(VoGeofence
                    .builder()
                    .setCenterAddress("TODO:重新计算")
                    .setLat(chooseLatLng.latitude)
                    .setLon(chooseLatLng.longitude)
                    .setRadius(computeRadius())
                    .build());
        }
    };

    AMapGestureListener mapGestureListener = new AMapGestureListener() {
        @Override
        public void onDoubleTap(float v, float v1) {

        }

        @Override
        public void onSingleTap(float v, float v1) {

        }

        @Override
        public void onFling(float v, float v1) {

        }

        @Override
        public void onScroll(float v, float v1) {

        }

        @Override
        public void onLongPress(float v, float v1) {

        }

        @Override
        public void onDown(float v, float v1) {

        }

        @Override
        public void onUp(float v, float v1) {

        }

        @Override
        public void onMapStable() {

        }
    };
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {


        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    //定位成功回调信息，设置相关消息
                    amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                    amapLocation.getLatitude();//获取纬度
                    amapLocation.getLongitude();//获取经度
                    amapLocation.getAccuracy();//获取精度信息
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date(amapLocation.getTime());
                    df.format(date);//定位时间
                    amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                    amapLocation.getCountry();//国家信息
                    amapLocation.getProvince();//省信息
                    amapLocation.getCity();//城市信息
                    amapLocation.getDistrict();//城区信息
                    amapLocation.getStreet();//街道信息
                    amapLocation.getStreetNum();//街道门牌号信息
                    amapLocation.getCityCode();//城市编码
                    amapLocation.getAdCode();//地区编码
                    amapLocation.getAoiName();//获取当前定位点的AOI信息

                    myLocation = amapLocation;

                    Log.e("pcw", "lat : " + myLocation.getLatitude() + " lon : " + myLocation.getLongitude()
                            + " address:" + amapLocation.getCity() + amapLocation.getDistrict() + amapLocation.getStreet() + amapLocation.getStreetNum()
                            + " Address:" + amapLocation.getAddress()
                            + " Aoi:" + amapLocation.getAoiName());

                } else {
                    //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                }
            }
        }
    };


    public static final class Builder {
        private String title;

        Builder() {
        }

        public Builder title(String value) {
            this.title = value;
            return this;
        }


        public FragmentEditstepGeofence build() {
            String missing = "";
            if (title == null) {
                missing += " title";
            }

            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }
            FragmentEditstepGeofence fragment = new FragmentEditstepGeofence();
            fragment.setTitle(title);
            return fragment;
        }
    }

}
