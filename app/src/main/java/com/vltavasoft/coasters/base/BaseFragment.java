package com.vltavasoft.coasters.base;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.vltavasoft.coasters.Billing.InAppBillingResources;
import com.vltavasoft.coasters.R;
import com.vltavasoft.coasters.database.Coaster;
import com.vltavasoft.coasters.database.DataHelper;

import java.util.ArrayList;
import java.util.List;

public class BaseFragment extends Fragment {

    private List<Coaster> mCoasters;
    private RecyclerView mRecycler;
    private FloatingActionButton mBtnAdd;
    private final CoastersAdapter mCoasterAdapter = new CoastersAdapter();
    private CoastersAdapter.OnItemClickListener mListener;

    private BillingProcessor bp;
    private boolean initialize = false;    // готовность к покупке

    static SharedPreferences mPreferences;
    static final String BUYING_KEY = "prefUnblock";
    public static final String PREF_FILENAME = "com.vltavasoft.coasters";
    boolean mBuyingStatus;                   // статус покупки (FALSE-не разблокировано)
    Context mContext;


    public static BaseFragment newInstance() {
        BaseFragment fragment = new BaseFragment();

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;

        bp = new BillingProcessor(context,
                InAppBillingResources.getRsaKey(),
                InAppBillingResources.getMerchantId(),
                new BillingProcessor.IBillingHandler() {
                    @Override
                    public void onProductPurchased(@NonNull String productId, @Nullable TransactionDetails details) {
                        if (bp.isPurchased(productId)) {
                            Log.d("BILLINGERR", "OnPurchased ");
                            SharedPreferences.Editor editor = mPreferences.edit();
                            editor.putBoolean(BUYING_KEY, true);
                            editor.commit();
                            restartDialog();
                        } else {
                            Log.d("BILLINGERR", "Nto bp.isPurchased ");
                        }
                    }

                    @Override
                    public void onPurchaseHistoryRestored() {

                    }

                    @Override
                    public void onBillingError(int errorCode, @Nullable Throwable error) {
                        Log.d("BILLINGERR", "ErrorCode-> " + errorCode);
                    }

                    @Override
                    public void onBillingInitialized() {
                        initialize = true;
                    }
                });

        mPreferences = getActivity().getSharedPreferences(PREF_FILENAME, Context.MODE_PRIVATE);
        mBuyingStatus = mPreferences.getBoolean(BUYING_KEY, false);
    }

    private void restartDialog() {
        AlertDialog.Builder builder;
        View alertLayout = View.inflate(mContext, R.layout.dialog_restart, null);
        builder = new AlertDialog.Builder(mContext, R.style.Theme_AppCompat_Dialog_Alert);
        builder.setView(alertLayout);
        builder.setCancelable(false);
        builder.setPositiveButton(getString(R.string.ans_restart),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        restartApp();
                    }
                });
        builder.show();
    }

    private void restartApp() {
        Intent restartIntent = mContext.getPackageManager().getLaunchIntentForPackage(mContext.getPackageName());
        if (restartIntent != null) {
            restartIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            mContext.startActivity(restartIntent);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fr_base, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mRecycler = view.findViewById(R.id.recycler);
        mBtnAdd = view.findViewById(R.id.btn_add);
        mCoasters = this.getmCoasters();

        getActivity().setTitle(R.string.app_name);

        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // mBuyingStatus: true - enabled(куплено) | false - disabled (не куплено)

                if (mCoasterAdapter.getItemCount() >= 2 && !mBuyingStatus) {
                    if (initialize) {
                        bp.purchase(getActivity(), "android.test.purchased");
                        //InAppBillingResources.getSkuUnblock());
                    }

                    SharedPreferences.Editor editor = mPreferences.edit();
                    editor.putBoolean(BUYING_KEY, true);
                    editor.commit();

                } else {
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fr_start_container, AddNewCoasterFragment.newInstance())
                            .commit();
                }
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mRecycler.setAdapter(mCoasterAdapter);
        mCoasterAdapter.addData(mCoasters);
        mCoasterAdapter.setListener(mListener);
        mRecycler.addItemDecoration(new RecyclerDecorator(10, 3));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setQueryHint("Example \"Hugarden\"");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mCoasterAdapter.filter(newText);
                return true;
            }
        });
    }

    @Override
    public void onDetach() {
        mListener = null;
        super.onDetach();
    }

    public List<Coaster> getmCoasters() {
        DataHelper dataHelper = new DataHelper();
        return dataHelper.getAllCoasters();
    }

}
