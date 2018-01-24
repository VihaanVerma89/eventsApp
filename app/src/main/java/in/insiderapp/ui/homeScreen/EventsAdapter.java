package in.insiderapp.ui.homeScreen;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

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
            .apply(new RequestOptions().placeholder(new ColorDrawable(ContextCompat.getColor
                    (mContext,R .color.event_image_placeholder))))
            .transition(withCrossFade())
            .into(holder.eventIV);

        String categoryName = event.getCategoryId().getName();
        String categoryColor = event.getCategoryId().getDisplayDetails().getColour();

        holder.eventCategoryTV.setBackgroundResource(R.drawable.bg_rectangle);
        GradientDrawable drawable = (GradientDrawable) holder.eventCategoryTV.getBackground();

        if(TextUtils.isEmpty(categoryColor))
        {
//            holder.eventCategoryTV.setBackgroundColor(mContext.getResources().getColor(R.color
//                    .default_event_color));
            drawable.setColor(mContext.getResources().getColor(R.color.default_event_color));
        }
        else{
//            holder.eventCategoryTV.setBackgroundColor(Color.parseColor(categoryColor));
            drawable.setColor(Color.parseColor(categoryColor));
        }
        holder.eventCategoryTV.setText(categoryName);
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
        TextView eventCategoryTV;
        TextView eventNameTV;
        TextView eventTimeTV;
        TextView eventLocationTV;
        TextView eventFeeTV;

        public EventViewHolder(View itemView) {
            super(itemView);
            eventIV = itemView.findViewById( R.id.eventIV );
            eventCategoryTV = itemView.findViewById( R.id.eventCategoryTV );
            eventNameTV = itemView.findViewById( R.id.eventNameTV);
            eventTimeTV = itemView.findViewById( R.id.eventTimeTV );
            eventLocationTV = itemView.findViewById( R.id.eventLocationTV );
            eventFeeTV = itemView.findViewById( R.id.eventFeeTV );
        }
    }


}
