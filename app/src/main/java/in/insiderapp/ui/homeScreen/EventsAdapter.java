package in.insiderapp.ui.homeScreen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import in.insiderapp.R;
import in.insiderapp.network.models.Event;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by vihaanverma on 24/01/18.
 */

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventViewHolder>{

    interface EventsListener{
        void onEventsClicked(int position);
    }

    private Context mContext;
    private List<Event> mEvents;
    private EventsListener mListener;

    public EventsAdapter(Context context, EventsListener listener, List<Event> events) {
        mContext = context;
        mEvents = events;
        mListener = listener;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.mContext).inflate(R.layout.item_event, parent, false);
        EventViewHolder viewHolder= new EventViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        Event event = mEvents.get(position);

        Glide.with(mContext)
            .load(event.getHorizontalCoverImage())
            .transition(withCrossFade())
            .into(holder.eventIV);

        holder.eventNameTV.setText(event.getName());
        holder.eventTimeTV.setText(event.getVenueDateString());
        holder.eventLocationTV.setText(event.getVenueName());
        if(event.getPriceDisplayString().equalsIgnoreCase("0"))
        {
            holder.eventFeeTV.setText("FREE");
        }
        else{
            String price = new StringBuilder(mContext.getString(R.string.ruppee_symbol)).append
                    (event.getPriceDisplayString()).toString();
            holder.eventFeeTV.setText(price);
        }

    }


    @Override
    public int getItemCount() {
        return mEvents.size();
    }


    class EventViewHolder extends RecyclerView.ViewHolder {

        ImageView eventIV;
        TextView eventNameTV;
        TextView eventTimeTV;
        TextView eventLocationTV;
        TextView eventFeeTV;

        public EventViewHolder(View itemView) {
            super(itemView);
            eventIV = (ImageView)itemView.findViewById( R.id.eventIV );
            eventNameTV = (TextView)itemView.findViewById( R.id.eventNameTV);
            eventTimeTV = (TextView)itemView.findViewById( R.id.eventTimeTV );
            eventLocationTV = (TextView)itemView.findViewById( R.id.eventLocationTV );
            eventFeeTV = (TextView)itemView.findViewById( R.id.eventFeeTV );
        }
    }


}
