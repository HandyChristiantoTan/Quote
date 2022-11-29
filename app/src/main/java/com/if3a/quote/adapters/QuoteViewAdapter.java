package com.if3a.quote.adapters;

    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.TextView;
    import android.widget.Toast;

    import androidx.annotation.NonNull;
    import androidx.recyclerview.widget.RecyclerView;

    import com.if3a.quote.R;
    import com.if3a.quote.models.Quote;

    import java.util.ArrayList;
    import java.util.List;

public class QuoteViewAdapter extends RecyclerView.Adapter<QuoteViewAdapter.ViewHolder> {
    private List<Quote> quoteList = new ArrayList<>();

    public QuoteViewAdapter(List<Quote> quoteList) {
        this.quoteList = quoteList;
    }

    @NonNull
    @Override
    public QuoteViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_quote, parent, false);
        ViewHolder holder = new ViewHolder(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuoteViewAdapter.ViewHolder holder, int position) {
        Quote QM = quoteList.get(position);

        holder.tvText.setText(QM.getText());
        holder.tvAuthor.setText(QM.getAuthor());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(holder.itemView.getContext(),
                        "Author : " + QM.getAuthor(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return quoteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvText, tvAuthor;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvText = itemView.findViewById(R.id.tv_text);
            tvAuthor = itemView.findViewById(R.id.tv_author);
        }
    }
}
