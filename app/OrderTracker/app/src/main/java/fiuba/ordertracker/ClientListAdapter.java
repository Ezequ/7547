package fiuba.ordertracker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import fiuba.ordertracker.pojo.Client;

/**
 *
 */
public class ClientListAdapter extends RecyclerView.Adapter<ClientListAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    List<Client> data = Collections.emptyList();

    public ClientListAdapter(Context context, List<Client> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;

        // Show message notifying there are no clients
        if (this.data.size() == 0){
            Toast.makeText(context, "No hay clientes en el sistema", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.client_list_row, null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Client current = this.data.get(position);
        holder.name.setText(current.getApenom());
        holder.address.setText(current.getDireccion());
        holder.distance.setText(current.getId());

        // Set listener to manage clicks on items from the RecyclerView
        holder.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                System.out.println("*********** Click on item ***********");

                Intent intent = new Intent(view.getContext(), ClientDetailActivity.class);
                intent.putExtra("name", data.get(position).getApenom());
                intent.putExtra("client_id", data.get(position).getId());
                intent.putExtra("address", data.get(position).getDireccion());
                intent.putExtra("telephone", data.get(position).getTelefono());
                intent.putExtra("distance", data.get(position).getTelefono());

                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name;
        TextView address;
        TextView distance;
        private OnItemClickListener clickListener;

        public MyViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.client_list_row_name);
            address = (TextView) itemView.findViewById(R.id.client_list_row_address);
            distance = (TextView) itemView.findViewById(R.id.client_list_row_distance);

            // Set listener to the item view
            itemView.setOnClickListener(this);
        }

        public void setOnItemClickListener(OnItemClickListener clickListener) {
            this.clickListener = clickListener;
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(view, getAdapterPosition()); // or getLayoutPosition() ??
        }
    }

    /**
     * Interface for handling clicks
     */
    private interface OnItemClickListener {
        /**
         * Called when the view is clicked
         * @param view that is clicked
         * @param position of the clicked item
         */
        public void onItemClick(View view, int position);

    }

}
