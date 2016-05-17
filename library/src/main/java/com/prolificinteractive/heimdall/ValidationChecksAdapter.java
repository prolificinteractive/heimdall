package com.prolificinteractive.heimdall;

import android.graphics.drawable.Drawable;
import android.os.Build;
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

  private final int itemTextAppearance;
  private final Drawable itemDrawableMatch;
  private final Drawable itemDrawableNoMatch;

  public ValidationChecksAdapter(
      final int itemTextAppearance,
      final Drawable itemDrawableMatch,
      final Drawable itemDrawableNoMatch) {

    this.itemTextAppearance = itemTextAppearance;
    this.itemDrawableMatch = itemDrawableMatch;
    this.itemDrawableNoMatch = itemDrawableNoMatch;
  }

  @Override
  public ValidationChecksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new ViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_validation, parent, false));
  }

  @Override
  public void onBindViewHolder(ValidationChecksAdapter.ViewHolder viewHolder, int position) {
    final ValidationCheck item = items.get(position);

    viewHolder.textView.setText(item.title);
    if (itemDrawableMatch != null && itemDrawableNoMatch != null) {
      viewHolder.textView.setCompoundDrawablesWithIntrinsicBounds(
          item.isMatched ? itemDrawableMatch : itemDrawableNoMatch, null, null, null);
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      viewHolder.textView.setTextAppearance(itemTextAppearance);
    } else {
      viewHolder.textView.setTextAppearance(viewHolder.textView.getContext(), itemTextAppearance);
    }
    viewHolder.textView.setSelected(item.isMatched);
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