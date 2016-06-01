package in.srain.cube.sample.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import in.srain.cube.request.RequestCacheManager;
import in.srain.cube.sample.R;
import in.srain.cube.sample.activity.PagerTabIndicatorActivity;
import in.srain.cube.sample.activity.TitleBaseFragment;
import in.srain.cube.sample.ui.fragment.imagelist.BigListViewFragment;
import in.srain.cube.sample.ui.fragment.imagelist.GridListViewFragment;
import in.srain.cube.sample.ui.fragment.imagelist.SmallListViewFragment;
import in.srain.cube.util.LocalDisplay;
import in.srain.cube.views.block.BlockListAdapter;
import in.srain.cube.views.block.BlockListView;
import in.srain.cube.views.block.BlockListView.OnItemClickListener;

import java.util.ArrayList;

public class HomeFragment extends TitleBaseFragment {

    private BlockListView mBlockListView;
    private ArrayList<ItemInfo> mItemInfos = new ArrayList<HomeFragment.ItemInfo>();

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        mBlockListView = (BlockListView) view.findViewById(R.id.ly_home_container);

        setHeaderTitle("Cube Demo");

        mItemInfos.add(new ItemInfo("Big Image List", "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStatck(BigListViewFragment.class, null);
            }
        }));

        mItemInfos.add(new ItemInfo("Grid Image List", "#b8ebf7", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStatck(GridListViewFragment.class, null);
            }
        }));

        mItemInfos.add(new ItemInfo("Small Image List", "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStatck(SmallListViewFragment.class, null);
            }
        }));

        mItemInfos.add(new ItemInfo("API Request", "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStatck(RequestDemoFragment.class, null);
            }
        }));

        mItemInfos.add(new ItemInfo("Dot View", "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStatck(DotViewFragment.class, null);
            }
        }));

        mItemInfos.add(new ItemInfo("More Action", "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStatck(MoreActionViewFragment.class, null);
            }
        }));
        mItemInfos.add(new ItemInfo("ImageLoader Management", "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStatck(ImageLoaderManagementFragment.class, null);
            }
        }));

        mItemInfos.add(new ItemInfo("RequestCache Management", "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                getContext().pushFragmentToBackStatck(RequestCacheManagementFragment.class, null);
            }
        }));
        mItemInfos.add(new ItemInfo("Tab", "#4d90fe", new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Class<?> dstClassName = PagerTabIndicatorActivity.class;
                intent.setClass(getActivity(), dstClassName);
                startActivity(intent);
            }
        }));

        setupList();
        return view;
    }

    private int mSize = 0;

    public void setupList() {

        mSize = (LocalDisplay.SCREEN_WIDTH_PIXELS - LocalDisplay.dp2px(25 + 5 + 5)) / 3;

        int horizontalSpacing = LocalDisplay.dp2px(5);
        int verticalSpacing = LocalDisplay.dp2px(10.5f);

        mBlockListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(View v, int position) {
                mBlockListAdapter.getItem(position).mOnClickListener.onClick(v);
            }
        });

        mBlockListAdapter.setSpace(horizontalSpacing, verticalSpacing);
        mBlockListAdapter.setBlockSize(mSize, mSize);
        mBlockListAdapter.setColumnNum(3);
        mBlockListView.setAdapter(mBlockListAdapter);
        mBlockListAdapter.displayBlocks(mItemInfos);
    }

    private BlockListAdapter<ItemInfo> mBlockListAdapter = new BlockListAdapter<HomeFragment.ItemInfo>() {

        @Override
        public View getView(LayoutInflater layoutInflater, int position) {
            ItemInfo itemInfo = mBlockListAdapter.getItem(position);

            ViewGroup view = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.item_home, null);

            TextView textView = ((TextView) view.findViewById(R.id.tv_item_home_title));
            textView.setText(itemInfo.mTitle);
            view.setBackgroundColor(itemInfo.getColor());
            return view;
        }
    };

    @Override
    protected boolean enableDefaultBack() {
        return false;
    }

    private static class ItemInfo {
        private String mColor;
        private String mTitle;
        private OnClickListener mOnClickListener;

        public ItemInfo(String title, String color, OnClickListener onClickListener) {
            mTitle = title;
            mColor = color;
            mOnClickListener = onClickListener;
        }

        private int getColor() {
            return Color.parseColor(mColor);
        }
    }
}
