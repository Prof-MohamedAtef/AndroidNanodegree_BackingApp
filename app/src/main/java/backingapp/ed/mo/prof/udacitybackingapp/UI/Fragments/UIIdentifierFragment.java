package backingapp.ed.mo.prof.udacitybackingapp.UI.Fragments;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import backingapp.ed.mo.prof.udacitybackingapp.AsyncTasks.GetRecipesAsyncTask;
import backingapp.ed.mo.prof.udacitybackingapp.Data.OptionsEntity;
import backingapp.ed.mo.prof.udacitybackingapp.R;
import backingapp.ed.mo.prof.udacitybackingapp.UI.Adapter.RecipesMasterListAdapter;
import backingapp.ed.mo.prof.udacitybackingapp.Util;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by Prof-Mohamed Atef on 10/21/2018.
 */

public class UIIdentifierFragment extends Fragment implements GetRecipesAsyncTask.OnTaskCompleted {

    @BindView(R.id.recycler_view_recipes) RecyclerView recyclerView;
    View rootView;

    private Unbinder unbinder;

    boolean TwoPane=false;
    boolean isInternetConnected;

    private boolean checkConnection() {
        return isInternetConnected=isConnected();
    }

    public boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE); //NetworkApplication.getInstance().getApplicationContext()/
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork!=null){
            return isInternetConnected= activeNetwork.isConnected();
        }else
            return isInternetConnected=false;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkConnection();
        if (isConnected()) {
            GetRecipesAsyncTask getRecipesAsyncTask = new GetRecipesAsyncTask(this);
            getRecipesAsyncTask.execute(getResources().getString(R.string.Recipes_API));
        } else {
            Toast.makeText(getActivity(), getResources().getString(R.string.pending_connection), Toast.LENGTH_SHORT).show();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_ui_identifier,container,false);
        unbinder=ButterKnife.bind(this,rootView);
        if (rootView.findViewById(R.id.two_pane)!=null){
            TwoPane=true;
            Util.TwoPane=true;
        }
        return rootView;
    }

    @Override
    public void onTaskCompleted(ArrayList<OptionsEntity> result) {
        RecipesMasterListAdapter mAdapter=new RecipesMasterListAdapter(getActivity(),result, TwoPane);
        mAdapter.notifyDataSetChanged();
        RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    public interface RecipeDataListener {
        void onRecipeSelected(OptionsEntity optionsEntity, boolean TwoPane);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}