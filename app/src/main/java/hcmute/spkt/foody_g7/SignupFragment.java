package hcmute.spkt.foody_g7;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignupFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Database db;
    private Users  user;
    public SignupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignupFragment newInstance(String param1, String param2) {
        SignupFragment fragment = new SignupFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText userName = (EditText) view.findViewById(R.id.eUserName);
        EditText password = (EditText) view.findViewById(R.id.ePassword);
        EditText rePassword = (EditText) view.findViewById(R.id.eRePassword);
        EditText phone = (EditText) view.findViewById(R.id.ePhone);
        EditText address= (EditText) view.findViewById(R.id.eAddress);
        Button button = (Button) view.findViewById(R.id.btnSignUp);
        View viewSignIn = view.findViewById(R.id.viewSignIn);
        db = new Database(getActivity());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (userName.getText().toString().matches("")
                        ||password.getText().toString().matches("")
                        ||phone.getText().toString().matches("")
                        ||address.getText().toString().matches("")) {
                    Toast.makeText(getActivity(), "All fields are required", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!password.getText().toString().equals(rePassword.getText().toString())){
                    Toast.makeText(getActivity(), "Password and confirm password does not match", Toast.LENGTH_LONG).show();
                    return;
                }
                Boolean isExist=checkUserName(userName.getText().toString());
                if(isExist){
                    Toast.makeText(getActivity(), "Username have existed, please choose another one", Toast.LENGTH_LONG).show();
                }else{
                    user=new Users(userName.getText().toString(),rePassword.getText().toString(),phone.getText().toString(),address.getText().toString());
                    createUser(user);
                    Toast.makeText(getActivity(), "Successful account registration", Toast.LENGTH_LONG).show();
                    replaceFragment(new SignInFragment());

                }

            }
        });
        viewSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new SignInFragment());
            }
        });
    }
    private Boolean checkUserName(String username){

        Cursor cursor =db.getData("SELECT * FROM User WHERE username='"+username+"'");
        if(cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }
    private void createUser (Users user){
        String query="INSERT INTO Users VALUES(null,'"+user.getUsername()+"','"+user.getPassword()+"','"+user.getPhone()+"','"+user.getAddress()+"')";
        db.queryData(query);
    }
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();

    }
}