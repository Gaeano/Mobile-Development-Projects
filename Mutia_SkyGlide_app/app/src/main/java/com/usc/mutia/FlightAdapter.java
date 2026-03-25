package com.usc.mutia;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.FlightViewHolder> {

    private List<FlightModel> flightList;
    private String routeInfo;
    private OnFlightClickListener listener;

    public interface OnFlightClickListener {
        void onFlightClick(int position);
    }

    public FlightAdapter(List<FlightModel> flightList, String routeInfo, OnFlightClickListener listener) {
        this.flightList = flightList;
        this.routeInfo = routeInfo;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FlightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flight, parent, false);
        return new FlightViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightViewHolder holder, int position) {
        FlightModel flight = flightList.get(position);

        holder.tvAirline.setText(flight.getAirline());
        holder.tvRoute.setText(routeInfo);
        holder.tvTimes.setText(flight.getDepartureTime() + " - " + flight.getArrivalTime());
        holder.tvPrice.setText("PHP " + flight.getBasePrice());
    }

    @Override
    public int getItemCount() {
        return flightList.size();
    }

    public class FlightViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvAirline, tvRoute, tvTimes, tvPrice;
        OnFlightClickListener listener;

        public FlightViewHolder(@NonNull View itemView, OnFlightClickListener listener) {
            super(itemView);
            this.listener = listener;

            tvAirline = itemView.findViewById(R.id.tv_airline);
            tvRoute = itemView.findViewById(R.id.tv_route);
            tvTimes = itemView.findViewById(R.id.tv_times);
            tvPrice = itemView.findViewById(R.id.tv_price);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getBindingAdapterPosition();
            if (position != RecyclerView.NO_POSITION && listener != null) {
                listener.onFlightClick(position);
            }
        }
    }
}