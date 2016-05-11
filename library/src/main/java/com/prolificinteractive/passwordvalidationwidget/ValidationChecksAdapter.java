package com.prolificinteractive.passwordvalidationwidget;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ValidationChecksAdapter
    extends RecyclerView.Adapter<ValidationChecksAdapter.ViewHolder> {

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

  public void setMatch(final Pattern pattern) {
    // TODO optimize
    for (int i = 0; i < items.size(); i++) {
      final ValidationCheck check = items.get(i);
      if (pattern.equals(check.regexPattern)) {
        check.setMatched(true);
        notifyDataSetChanged();
        break;
      }
    }
  }

  public void setNoMatch(final Pattern pattern) {
    // TODO optimize
    for (int i = 0; i < items.size(); i++) {
      final ValidationCheck check = items.get(i);
      if (pattern.equals(check.regexPattern)) {
        check.setMatched(false);
        notifyDataSetChanged();
        break;
      }
    }
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {

    public TextView textView;

    public ViewHolder(View itemView) {
      super(itemView);

      textView = (TextView) itemView.findViewById(R.id.validation_item_title_text);
    }
  }
}
