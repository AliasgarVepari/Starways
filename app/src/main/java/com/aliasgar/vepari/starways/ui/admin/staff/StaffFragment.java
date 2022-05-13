package com.aliasgar.vepari.starways.ui.admin.staff;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aliasgar.vepari.starways.databinding.FragmentStaffBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StaffFragment extends Fragment {



    private StaffViewModel staffViewModel;
    private FragmentStaffBinding binding;
    private List<Staff> staffItems;
    RecyclerViewAdapter adapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        staffViewModel =
                new ViewModelProvider(this).get(StaffViewModel.class);

        staffItems = new ArrayList<>();
        binding = FragmentStaffBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final RecyclerView rcView = binding.staffRcview;
        final TextView textView = binding.textStaff;
        staffViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);

            }
        });

        RecyclerView recyclerView = rcView;
        LoadRcViewitems();
        adapter = new RecyclerViewAdapter(staffItems,this.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        return root;
    }

//    public void initRc(RecyclerView rcView){
//
//        //LoadRcViewitems();
//        RecyclerView recyclerView = rcView;
//        RecyclerViewAdapter adapter = new RecyclerViewAdapter(staffItems,this.getContext());
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
//    }

    private void LoadRcViewitems(){
        ProgressDialog pg = new ProgressDialog(this.getContext());
        pg.setMessage("LOADING...");
        pg.show();


        RetrofitClient.getStaffApi().callStaff().enqueue(new Callback<List<Staff>>() {
            @Override
            public void onResponse(Call<List<Staff>> call, Response<List<Staff>> response) {
                if(response.isSuccessful() && response.body() != null){
                    staffItems.addAll(response.body());
                    System.out.println(Arrays.toString(staffItems.toArray()));
                    adapter.notifyItemInserted(staffItems.size()-1);
                    pg.dismiss();

                }
            }

            @Override
            public void onFailure(Call<List<Staff>> call, Throwable t) {
                Toast.makeText(getContext(),"Error: "+ t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
//        initRc(binding.staffRcview);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}