package com.roomorama.caldroid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;

import com.caldroid.R;

/**
 * DateGridFragment contains only 1 gridview with 7 columns to display all the
 * dates within a month.
 * <p/>
 * Client must supply gridAdapter and onItemClickListener before the fragment is
 * attached to avoid complex crash due to fragment life cycles.
 *
 * @author thomasdao
 */
public class DateGridFragment extends Fragment {
    private View rootView;
    private GridView weekGridView;
    private GridView monthGridView;
    private CaldroidGridAdapter monthGridAdapter;
    private WeekdayArrayAdapter weekdayGridAdapter;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    private int gridViewRes = 0;
    private int themeResource = 0;

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public OnItemLongClickListener getOnItemLongClickListener() {
        return onItemLongClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public CaldroidGridAdapter getMonthGridAdapter() {
        return monthGridAdapter;
    }

    public void setMonthGridAdapter(CaldroidGridAdapter gridAdapter) {
        this.monthGridAdapter = gridAdapter;
    }

    public void setWeekdayGridAdapter(WeekdayArrayAdapter gridAdapter) {
        this.weekdayGridAdapter = gridAdapter;
    }

    public GridView getMonthGridView() {
        return monthGridView;
    }

    public GridView getWeekGridView() {
        return weekGridView;
    }

    public void setGridViewRes(int gridViewRes) {
        this.gridViewRes = gridViewRes;
    }

    private void setupGridView() {


        // Client normally needs to provide the adapter and onItemClickListener
        // before the fragment is attached to avoid complex crash due to
        // fragment life cycles
        if (monthGridAdapter != null) {
            monthGridView.setAdapter(monthGridAdapter);
        }

        if (onItemClickListener != null) {
            monthGridView.setOnItemClickListener(onItemClickListener);
        }
        if (onItemLongClickListener != null) {
            monthGridView.setOnItemLongClickListener(onItemLongClickListener);
        }

        if (weekdayGridAdapter != null) {
            weekGridView.setAdapter(weekdayGridAdapter);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // If gridViewRes is not valid, use default fragment layout
        if (gridViewRes == 0) {
            gridViewRes = R.layout.date_grid_fragment;
        }

        if (themeResource == 0) {
            if (monthGridAdapter != null) {
                themeResource = monthGridAdapter.getThemeResource();
            }
        }

        if (rootView == null) {
            LayoutInflater localInflater = CaldroidFragment.getLayoutInflater(getActivity(),
                    inflater, themeResource);

            rootView = localInflater.inflate(gridViewRes, container, false);
            weekGridView = (GridView) rootView.findViewById(R.id.weekday_gridview);
            monthGridView = (GridView) rootView.findViewById(R.id.calendar_gridview);
            setupGridView();
        } else {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        }

        return rootView;
    }

}
