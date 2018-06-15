package com.tribikram.myapplication.Activities;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tribikram.myapplication.R;
import com.tribikram.myapplication.models.User;
import com.tribikram.myapplication.models.UserDAO;
import com.tribikram.myapplication.utils.MyDatabase;

import java.util.ArrayList;
import java.util.List;

public class RoomActivity extends AppCompatActivity {

    private EditText et_name, et_age, et_address;

    private MyDatabase db;
    private UserDAO userDAO;
    private RecyclerView recyclerView;
    List<User> users;
    DatabaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        recyclerView= findViewById(R.id.recylcerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        db = MyDatabase.getDatabase(this);
        userDAO = db.userDAO();

        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        et_address = findViewById(R.id.et_address);


        //List<User> user = userDAO.getAllUsers();
        //Log.d("legend", users.get(0).getName() );


       /* new GetUserTask().execute();*/
       /* new Thread(new Runnable() {
            @Override
            public void run() {
                List<User> users = userDAO.getAllUsers();
                Log.d("legend", users.get(0).getName() );
            }
        }).start();*/
       new GetUserTask().execute();
    }
    public class DatabaseAdapter extends RecyclerView.Adapter<DatabaseAdapter.DatabaseViewHolder>{
        private Context context;
        private List<User> userList;
        private LayoutInflater inflater;

        public DatabaseAdapter(Context context, List<User> userList){
            this.context= context;
            this.userList= userList;
            inflater= LayoutInflater.from(context);
        }
        @Override
        public DatabaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
           View view = inflater.inflate(R.layout.list_item_view_database, parent, false);

           DatabaseViewHolder holder= new DatabaseViewHolder(view);
           return holder;
        }

        @Override
        public void onBindViewHolder(DatabaseViewHolder holder, int position) {
           User user= userList.get(position);
            holder.tv_1.setText(user.getName());
            holder.tv_2.setText(user.getAddress());
            holder.tv_3.setText(String.valueOf(user.getAge()));
        }

        @Override
        public int getItemCount() {
            return userList.size();
        }

        public class DatabaseViewHolder extends RecyclerView.ViewHolder{
            private TextView tv_1, tv_2, tv_3;
            public DatabaseViewHolder(View itemView) {
                super(itemView);
                tv_1 = itemView.findViewById(R.id.tv_1);
                tv_2= itemView.findViewById(R.id.tv_2);
                tv_3= itemView.findViewById(R.id.tv_3);

            }
        }
    }

    public void save(View view) {
        String name = et_name.getText().toString();
        int age = Integer.parseInt(et_age.getText().toString());
        String address = et_address.getText().toString();

        User user = new User(name, age, address);

        new SaveTask().execute(user);


    }
    //ASynTask

    private class SaveTask extends AsyncTask<User, Void, Void> {

        @Override
        protected Void doInBackground(User... users) {
            userDAO.insert(users[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }
    }

    private class GetUserTask extends AsyncTask<Void, Void, List<User>> {
        @Override
        protected List<User> doInBackground(Void... voids) {
            List<User> users = userDAO.getAllUsers();
            return users;
        }

        @Override
        protected void onPostExecute(List<User> users) {
            users = userDAO.getAllUsers();
            adapter = new DatabaseAdapter(RoomActivity.this, users);
            recyclerView.setAdapter(adapter);
        }
    }
}
