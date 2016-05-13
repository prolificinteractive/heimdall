package com.prolificinteractive.passwordvalidationwidget;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class ValidationChecksAdapter
    extends RecyclerView.Adapter<ValidationChecksAdapter.ViewHolder> {

  private static final String TAG = ValidationChecksAdapter.class.getSimpleName();

  private final List<ValidationCheck> items = new ArrayList<>();

  @Override
  public ValidationChecksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new ViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_validation, parent, false));
  }

  @Override
  public void onBindViewHolder(ValidationChecksAdapter.ViewHolder viewHolder, int position) {
    final ValidationCheck item = items.get(position);
    viewHolder.textView.setText(item.title);

    viewHolder.textView.setAlpha(item.isMatched ? 1.0f : .5f);
  }

  @Override
  public int getItemCount() {
    return items.size();
  }

  public void swapData(final List<ValidationCheck> fields) {
    this.items.clear();

    if (fields != null) {
      this.items.addAll(fields);
    }

    notifyDataSetChanged();
  }

  public void setMatch(final ValidationCheck check) {
    final int matchedIndex = items.indexOf(check);
    Log.d(TAG, "index for match: " + matchedIndex);
    final ValidationCheck matched = items.get(matchedIndex);
    matched.setMatched(true);
  }

  public void setNoMatch(final ValidationCheck check) {
    final int unmatchedIndex = items.indexOf(check);
    Log.d(TAG, "index for no match: " + unmatchedIndex);
    final ValidationCheck unmatched = items.get(unmatchedIndex);
    unmatched.setMatched(false);
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {

    public TextView textView;

    public ViewHolder(View itemView) {
      super(itemView);

      textView = (TextView) itemView.findViewById(R.id.validation_item_title_text);
    }
  }
}
