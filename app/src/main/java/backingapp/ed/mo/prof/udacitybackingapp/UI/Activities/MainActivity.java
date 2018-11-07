package backingapp.ed.mo.prof.udacitybackingapp.UI.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import backingapp.ed.mo.prof.udacitybackingapp.Data.OptionsEntity;
import backingapp.ed.mo.prof.udacitybackingapp.R;
import backingapp.ed.mo.prof.udacitybackingapp.UI.Fragments.UIIdentifierFragment;
import backingapp.ed.mo.prof.udacitybackingapp.Util;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MainActivity extends AppCompatActivity implements UIIdentifierFragment.RecipeDataListener{

    @BindView(R.id.fragment_container) LinearLayout linearLayout;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder= ButterKnife.bind(this);
        UIIdentifierFragment uiIdentifierFragment= new UIIdentifierFragment();
        getSupportFragmentManager().beginTransaction().replace(linearLayout.getId(),uiIdentifierFragment, null).commitAllowingStateLoss();
    }

    @Override
    public void onRecipeSelected(OptionsEntity optionsEntity, boolean TwoPane) {
        /*
        send via intent
        identify interface via UI
         */
        Intent intent = new Intent(this, DetailActivity.class);
        Util.RecipeID=optionsEntity.getRecipeID();
        if (TwoPane){
            intent.putExtra(getString(R.string.two_pane),TwoPane);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.startActivity(intent);
        }else {
            intent.putExtra(getString(R.string.two_pane),TwoPane);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.startActivity(intent);
        }
    }
}