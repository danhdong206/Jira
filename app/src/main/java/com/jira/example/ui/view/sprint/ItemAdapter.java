/*
 * Copyright 2014 Magnus Woxblom
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jira.example.ui.view.sprint;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jira.example.R;
import com.jira.example.model.Item;
import com.squareup.picasso.Picasso;
import com.woxthebox.draglistview.DragItemAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

class ItemAdapter extends DragItemAdapter<Pair<Long, Item>, ItemAdapter.ViewHolder> {

    private int mLayoutId;
    private int mGrabHandleId;
    private boolean mDragOnLongPress;
    private Context context;

    ItemAdapter(Context context, ArrayList<Pair<Long, Item>> list, int layoutId, int grabHandleId, boolean dragOnLongPress) {
        mLayoutId = layoutId;
        mGrabHandleId = grabHandleId;
        mDragOnLongPress = dragOnLongPress;
        setHasStableIds(true);
        setItemList(list);
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mLayoutId, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
        Item item = mItemList.get(position).second;

        if (viewHolder instanceof ViewHolder) {
            ((ViewHolder) viewHolder).bind(item, position);
        }
    }

    @Override
    public long getItemId(int position) {
        return mItemList.get(position).first;
    }

    class ViewHolder extends DragItemAdapter.ViewHolder {
        View rootView;
        @Bind(R.id.img_avatar)
        ImageView imgAvatar;
        @Bind(R.id.img_type)
        ImageView imgType;
        @Bind(R.id.img_priority)
        ImageView imgPriority;
        @Bind(R.id.txt_name)
        TextView txtName;
        @Bind(R.id.txt_summary)
        TextView txtSummary;
        @Bind(R.id.txt_link)
        TextView txtLink;

        ViewHolder(final View itemView) {
            super(itemView, mGrabHandleId, mDragOnLongPress);
            rootView = itemView;
            ButterKnife.bind(this, rootView);
        }

        public void bind(Item item, int position) {
            rootView.setTag(item);
            Picasso.with(context).load(R.drawable.ic_profile_holder)
                    .placeholder(R.drawable.ic_profile_holder)
//                    .transform(new CircleTransform())
                    .into(imgAvatar);
            switch (item.getIssueType().ordinal()){
                case Item.ISSUE_TYPE_STORY:
                    imgType.setImageResource(R.drawable.xml_ic_bookmark);
                    imgType.setBackgroundResource(R.drawable.xml_bg_story);
                    break;
                case Item.ISSUE_TYPE_DEFECT:
                    imgType.setImageResource(R.drawable.xml_ic_circle);
                    imgType.setBackgroundResource(R.drawable.xml_bg_defect);
                    break;
                case Item.ISSUE_TYPE_TASK:
                    imgType.setImageResource(R.drawable.xml_ic_tick);
                    imgType.setBackgroundResource(R.drawable.xml_bg_task);
                    break;
            }

            switch (item.getPriority().ordinal()){
                case Item.MINOR_PRIORITY:
                    imgPriority.setImageResource(R.drawable.xml_ic_arrow_down);
                    imgPriority.setColorFilter(context.getResources().getColor(R.color.green));
                    break;
                case Item.MAJOR_PRIORITY:
                    imgPriority.setImageResource(R.drawable.xml_ic_arrow_up);
                    imgPriority.setColorFilter(context.getResources().getColor(R.color.red));
                    break;
                case Item.CRITICAL_PRIORITY:
                    imgPriority.setColorFilter(context.getResources().getColor(R.color.red));
                    imgPriority.setImageResource(R.drawable.xml_ic_arrow_up_expand);
                    break;
            }

            txtName.setText(item.getName());
            txtSummary.setText(item.getSummary());
            txtLink.setText(item.getEpicLink());
            if(TextUtils.isEmpty(item.getEpicLink())){
                txtLink.setVisibility(View.GONE);
            } else {
                txtLink.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onItemClicked(View view) {
            Toast.makeText(view.getContext(), "Item clicked", Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean onItemLongClicked(View view) {
            Toast.makeText(view.getContext(), "Item long clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
    }
}
