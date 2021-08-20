package com.techakram.Online_Shop;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.gridlayout.widget.GridLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomePageAdapter extends RecyclerView.Adapter {
    RecyclerView.RecycledViewPool recycledViewPool;
    private List<HomePageModel> homePageModelList;

    public HomePageAdapter(List<HomePageModel> homePageModelList) {
        this.homePageModelList = homePageModelList;
        recycledViewPool=new RecyclerView.RecycledViewPool();
    }
    @Override
    public int getItemViewType(int position) {
        switch (homePageModelList.get(position).getType( )) {
            case 0:
                return HomePageModel.BANNER_SLIDER;
            case 1:
                return HomePageModel.STRIP_AD_BANNER;
            case 2:
                return HomePageModel.HORIZONTAL_PRODUCT_VIEW;
            case 3:
                return HomePageModel.GRID_PRODUCT_VIEW;
            default:
                return -1;
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case HomePageModel.BANNER_SLIDER:
                View bannerSliderview = LayoutInflater.from(parent.getContext( )).inflate(R.layout.sliding_add_layout, parent, false);
                return new BannerSliderViewholder(bannerSliderview);
            case HomePageModel.STRIP_AD_BANNER:
                View stripAdview = LayoutInflater.from(parent.getContext( )).inflate(R.layout.strip_add_layout, parent, false);
                return new StripAdBannerViewholder(stripAdview);
            case HomePageModel.HORIZONTAL_PRODUCT_VIEW:
                View horizontal_product_view = LayoutInflater.from(parent.getContext( )).inflate(R.layout.horizontal_scroll_layout, parent, false);
                return new HorizontalProductView(horizontal_product_view);
            case HomePageModel.GRID_PRODUCT_VIEW:
                View grid_product_view = LayoutInflater.from(parent.getContext( )).inflate(R.layout.grid_product_layout, parent, false);
                return new GridProductviewholder(grid_product_view);
            default:
                return null;
        }
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (homePageModelList.get(position).getType( )) {
            case HomePageModel.BANNER_SLIDER:
                List<SliderModel> sliderModelList = homePageModelList.get(position).getSliderModelList( );
                ((BannerSliderViewholder) holder).setBannerSlideviewPager(sliderModelList);
                break;
            case HomePageModel.STRIP_AD_BANNER:
                String resource = homePageModelList.get(position).getResource( );
                Log.d("check", String.valueOf(resource));
                String color = homePageModelList.get(position).getBackgroundColor( );
                ((StripAdBannerViewholder) holder).setstripAd(resource, color);
                break;
            case HomePageModel.HORIZONTAL_PRODUCT_VIEW:
                String layoutColor=homePageModelList.get(position).getBackgroundColor();
                String title=homePageModelList.get(position).getTitle();
                List<Horizontal_product_scroll_Model> horizontal_product_scroll_modelList
                        =homePageModelList.get(position).getHorizontal_product_scroll_modelList();
                List<WishListModel> viewAllProductList=homePageModelList.get(position).getViewAllProductList();
                ((HorizontalProductView) holder).setHorizontalProductLayout(horizontal_product_scroll_modelList,title,layoutColor,viewAllProductList);
            case HomePageModel.GRID_PRODUCT_VIEW:
                String layout_color=homePageModelList.get(position).getBackgroundColor();
                String horizontalLayoutTitle=homePageModelList.get(position).getTitle();
                List<Horizontal_product_scroll_Model> grid_product_scroll_modelList
                        =homePageModelList.get(position).getHorizontal_product_scroll_modelList();
                if (holder instanceof GridProductviewholder) {
                    ((GridProductviewholder) holder).setGridViewProductLayout(grid_product_scroll_modelList,horizontalLayoutTitle,layout_color);
                }
            default:
                return;
        }
    }
    @Override
    public int getItemCount() {
        return homePageModelList.size( );
    }
    public class BannerSliderViewholder extends RecyclerView.ViewHolder {
        private ViewPager bannerSlideviewPager;
        private SliderAdapter sliderAdapter;
        private int currentPage;
        private Timer timer;
        final private long Delay_Time = 3000;
        final private long Period_Time = 3000;
        //for infinite slider with coding...
        private List<SliderModel> arrangeList;
        public BannerSliderViewholder(@NonNull View itemView) {
            super(itemView);
            bannerSlideviewPager = itemView.findViewById(R.id.banner_slider_viewpager);
        }

        private void setBannerSlideviewPager(List<SliderModel> sliderModelList) {
            currentPage = 2;
            if(timer!=null)
            {
                timer.cancel();
            }
            arrangeList=new ArrayList<>();
            for (int x=0;x<sliderModelList.size();x++)
            {
               arrangeList.add(x,sliderModelList.get(x));

            }
            arrangeList.add(0,sliderModelList.get(sliderModelList.size()-2));
            arrangeList.add(1,sliderModelList.get(sliderModelList.size()-1));
            arrangeList.add(sliderModelList.get(0));
            arrangeList.add(sliderModelList.get(1));
            sliderAdapter = new SliderAdapter(arrangeList);
            //Log.d("akram", sliderModelList.);
            bannerSlideviewPager.setAdapter(sliderAdapter);
            bannerSlideviewPager.setClipToPadding(false);
            bannerSlideviewPager.setPageMargin(20);
            bannerSlideviewPager.setCurrentItem(currentPage);
            ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener( ) {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    currentPage = position;

                }

                @Override
                public void onPageScrollStateChanged(int i) {
                    if (i == ViewPager.SCROLL_STATE_IDLE) {
                        pageLooper(arrangeList);
                    }
                }
            };
            bannerSlideviewPager.addOnPageChangeListener(onPageChangeListener);
            startBannerSlideShow(arrangeList);
            //for touch banner..
            bannerSlideviewPager.setOnTouchListener(new View.OnTouchListener( ) {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    pageLooper(arrangeList);
                    stopBannerslideShow(arrangeList);
                    if (motionEvent.getAction( ) == MotionEvent.ACTION_UP) {
                        //finger touch
                        startBannerSlideShow(arrangeList);
                    }
                    return false;
                }
            });
        }
        private void pageLooper(List<SliderModel> sliderModelList) {
            if (currentPage == sliderModelList.size( ) - 2) {
                currentPage = 2;
                bannerSlideviewPager.setCurrentItem(currentPage, false);
            }
            if (currentPage == 1) {
                currentPage = sliderModelList.size( ) - 3;
                bannerSlideviewPager.setCurrentItem(currentPage, false);
            }
        }
        //for timer start...
        private void startBannerSlideShow(List<SliderModel> sliderModelList) {
            Handler handler = new Handler( );
            Runnable update = new Runnable( ) {
                @Override
                public void run() {
                    if (currentPage >= sliderModelList.size( )) {
                        currentPage = 1;
                    }
                    bannerSlideviewPager.setCurrentItem(currentPage++, true);
                }
            };
            timer = new Timer( );
            timer.schedule(new TimerTask( ) {
                @Override
                public void run() {
                    handler.post(update);
                }
            }, Delay_Time, Period_Time);
        }
        private void stopBannerslideShow(List<SliderModel> sliderModelList) {
            timer.cancel( );
        }
    }
    public class StripAdBannerViewholder extends RecyclerView.ViewHolder {
        private ImageView stripAdImage;
        private ConstraintLayout stripAdContainer;

        public StripAdBannerViewholder(@NonNull View itemView) {
            super(itemView);
            stripAdImage = itemView.findViewById(R.id.strip_add_image);
            stripAdContainer = itemView.findViewById(R.id.strip_add_container);
        }

        private void setstripAd(String resource, String color) {
            //stripAdImage.setImageResource(resource);
            Glide.with(itemView.getContext()).load(resource).apply(new RequestOptions())
                    .placeholder(R.drawable.mobile_icons).into(stripAdImage);
            stripAdContainer.setBackgroundColor(Color.parseColor(color));
        }
    }
    public class HorizontalProductView extends RecyclerView.ViewHolder {
        private ConstraintLayout container;
        private RecyclerView horizontalScrollProductRcyclerView;
        private TextView horizontalTitletextview;
        private Button horizontalViewAllProductButton;

        public HorizontalProductView(@NonNull View itemView) {
            super(itemView);
            container=itemView.findViewById(R.id.horizontal_scroll_layout);
            horizontalTitletextview = itemView.findViewById(R.id.horizontal_scroll_title);
            horizontalViewAllProductButton = itemView.findViewById(R.id.horizontal_scroll_button);
            horizontalScrollProductRcyclerView = itemView.findViewById(R.id.horizontal_scroll_recyclerView);
            horizontalScrollProductRcyclerView.setRecycledViewPool(recycledViewPool);
        }

        private void setHorizontalProductLayout(List<Horizontal_product_scroll_Model> horizontal_product_scroll_modelList, String title,String color,List<WishListModel> viewAllProductList) {
            container.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color)));
            horizontalTitletextview.setText(title);
            if (horizontal_product_scroll_modelList.size( ) > 8)
            {
                horizontalViewAllProductButton.setVisibility(View.VISIBLE);
                horizontalViewAllProductButton.setOnClickListener(new View.OnClickListener( ) {
                    @Override
                    public void onClick(View v) {
                        ViewAllGridOrHorizontalActivity.wishListModelList=viewAllProductList;
                        Intent viewAllIntent=new Intent(itemView.getContext(),ViewAllGridOrHorizontalActivity.class);
                        viewAllIntent.putExtra("layout_code",0);
                        viewAllIntent.putExtra("title",title);
                        itemView.getContext().startActivity(viewAllIntent);
                    }
                });
            }
            else
                {
                horizontalViewAllProductButton.setVisibility(View.INVISIBLE);
                }
                Horizontal_product_scroll_Adapter horizontal_product_scroll_adapter = new Horizontal_product_scroll_Adapter(horizontal_product_scroll_modelList);
                LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(itemView.getContext( ));
                linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
                horizontalScrollProductRcyclerView.setLayoutManager(linearLayoutManager1);
                horizontalScrollProductRcyclerView.setAdapter(horizontal_product_scroll_adapter);
                horizontal_product_scroll_adapter.notifyDataSetChanged( );
        }
    }
    public class GridProductviewholder extends RecyclerView.ViewHolder{
        private TextView grid_titleTextview;
        private Button grid_viewall_button;
        private GridLayout gridProductLayout;
        private ConstraintLayout gd_container;

        public GridProductviewholder(@NonNull View itemView) {
            super(itemView);
            gd_container=itemView.findViewById(R.id.container);
            grid_titleTextview=itemView.findViewById(R.id.grid_product_layout_title);
            grid_viewall_button=itemView.findViewById(R.id.grid_product_layout_viewAll_button);
            gridProductLayout=itemView.findViewById(R.id.grid_layout);
        }
        private void setGridViewProductLayout(List<Horizontal_product_scroll_Model> horizontal_product_scroll_modelList,String title,String color)
        {
            gd_container.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color)));
            grid_titleTextview.setText(title);
            ////////////////for loop access all items.
            for(int x=0;x<4;x++)
            {
                ImageView productImage=gridProductLayout.getChildAt(x).findViewById(R.id.Hs_product_image);
                TextView productTitle=gridProductLayout.getChildAt(x).findViewById(R.id.Hs_product_title);
                TextView productDiscription=gridProductLayout.getChildAt(x).findViewById(R.id.Hs_product_discription);
                TextView productPrice=gridProductLayout.getChildAt(x).findViewById(R.id.Hs_product_price);

                // productImage.setImageResource(horizontal_product_scroll_modelList.get(x).getProductImage());
                 Glide.with(itemView.getContext())
                         .load(horizontal_product_scroll_modelList.get(x).getProductImage())
                         .apply(new RequestOptions()).placeholder(R.drawable.mobile_icons)
                         .into(productImage);
                 productTitle.setText(horizontal_product_scroll_modelList.get(x).getProductTitle());
                 productDiscription.setText(horizontal_product_scroll_modelList.get(x).getProductDiscription());
                 productPrice.setText(horizontal_product_scroll_modelList.get(x).getGetProductPrice());
                 gridProductLayout.getChildAt(x).setBackgroundColor(Color.parseColor("#DAD4D4"));
                 gridProductLayout.getChildAt(x).setOnClickListener(new View.OnClickListener( ) {
                     @Override
                     public void onClick(View v) {
                         Intent productDetailsIntent=new Intent(itemView.getContext(),ProductDetailsActivity.class);
                         itemView.getContext().startActivity(productDetailsIntent);
                     }
                 });
            }
            grid_viewall_button.setOnClickListener(new View.OnClickListener( ) {
                @Override
                public void onClick(View v) {
                    ViewAllGridOrHorizontalActivity.horizontal_product_scroll_modelList=horizontal_product_scroll_modelList;
                    Intent viewAllIntent=new Intent(itemView.getContext(),ViewAllGridOrHorizontalActivity.class);
                    viewAllIntent.putExtra("layout_code",1);
                    itemView.getContext().startActivity(viewAllIntent);
                }
            });
        }
    }
}
