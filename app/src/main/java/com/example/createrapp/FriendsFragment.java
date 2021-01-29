package com.example.createrapp;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.createrapp.databinding.FragmentFriendsBinding;
import com.example.createrapp.databinding.ListGroupBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class FriendsFragment extends Fragment {
    private FragmentFriendsBinding binding;
    private NavController navController;

    private ExpandableListView expandableListView;
    private ExpListAdapter adapter;
    private ArrayList<String> listCategorias = new ArrayList<>();
    private HashMap<String, ArrayList<String>> mapChild = new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentFriendsBinding.inflate(inflater, container, false)).getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);


        ArrayList<String> listAmigosConectados = new ArrayList<>();
        ArrayList<String> listAmigosDesconectados = new ArrayList<>();

        listCategorias.add("Conectados");
        listCategorias.add("Desconectados");

        listAmigosConectados.add("Amigo Conectado Uno");
        listAmigosConectados.add("Amigo Conectado Dos");
        listAmigosConectados.add("Amigo Conectado Tres");
        listAmigosConectados.add("Amigo Conectado Cuatro");
        listAmigosConectados.add("Amigo Conectado Cinco");

        listAmigosDesconectados.add("Amigo Desconectado Uno");
        listAmigosDesconectados.add("Amigo Desconectado Dos");
        listAmigosDesconectados.add("Amigo Desconectado Tres");
        listAmigosDesconectados.add("Amigo Desconectado Cuatro");
        listAmigosDesconectados.add("Amigo Desconectado Cinco");
        listAmigosDesconectados.add("Amigo Desconectado Seis");
        listAmigosDesconectados.add("Amigo Desconectado Siete");
        listAmigosDesconectados.add("Amigo Desconectado Ocho");
        listAmigosDesconectados.add("Amigo Desconectado Nueve");
        listAmigosDesconectados.add("Amigo Desconectado Diez");

        mapChild.put(listCategorias.get(0), listAmigosConectados);
        mapChild.put(listCategorias.get(1), listAmigosDesconectados);

        adapter = new ExpListAdapter(listCategorias, mapChild);

        binding.connectedViewListFriends.setAdapter(adapter);

        binding.backFriends.setOnClickListener(v ->
                navController.popBackStack()
        );

        binding.anyadirAmigoBoton.setOnClickListener(v ->
            navController.navigate(R.id.anyadirAmigoFragment)
        );
    }
}

class ExpListAdapter extends BaseExpandableListAdapter{

    private ArrayList<String> listGroup;
    private Map<String, ArrayList<String>> listChild;

    public ExpListAdapter(ArrayList<String> listGroup, Map<String, ArrayList<String>> listChild) {
        this.listGroup = listGroup;
        this.listChild = listChild;
    }

    @Override
    public int getGroupCount() {
        return listGroup.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return listChild.get(listGroup.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return listGroup.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return listChild.get(listGroup.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_group, viewGroup, false);
        TextView tvGroup = (TextView) view.findViewById(R.id.lblListHeader);
        String tituloCategoria = String.valueOf(getGroup(i));
        tvGroup.setText(tituloCategoria);
        tvGroup.setTypeface(null, Typeface.BOLD);
        tvGroup.setTextColor(Color.BLACK);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        TextView tvChild = view.findViewById(R.id.lblListItem);
        String item = String.valueOf(getChild(i, i1));
        tvChild.setText(item);
        tvChild.setOnClickListener(v -> {
            Toast.makeText(viewGroup.getContext(), item, Toast.LENGTH_LONG).show();
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}