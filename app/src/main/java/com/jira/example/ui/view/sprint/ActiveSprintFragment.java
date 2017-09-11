package com.jira.example.ui.view.sprint;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jira.example.R;
import com.jira.example.dj.ActiveSprintModule;
import com.jira.example.model.Item;
import com.jira.example.network.response.ItemResponse;
import com.jira.example.ui.InjectableFragment;
import com.jira.example.ui.presenter.sprint.ActiveSprintPresenter;
import com.squareup.picasso.Picasso;
import com.woxthebox.draglistview.BoardView;
import com.woxthebox.draglistview.DragItem;
import com.woxthebox.draglistview.DragItemAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by admin on 9/9/17.
 */

public class ActiveSprintFragment extends InjectableFragment implements ActiveSprintView{
    public static final String TAG = ActiveSprintFragment.class.getSimpleName();
    public static final String TODO = "Todo";
    public static final String IN_PROGRESS = "InProgress";
    public static final String DONE = "Done";

    public static final int COLUMN_TODO = 0;
    public static final int COLUMN_IN_PROGRESS = 1;
    public static final int COLUMN_DONE = 2;

    @Bind(R.id.board_view)
    BoardView mBoardView;

    @Inject
    ActiveSprintPresenter activeSprintPresenter;

    private static int sCreatedItems = 0;
    private int mColumns;

    @Override
    public List<Object> getModules() {
        List<Object> modules = new ArrayList<>();
        modules.add(new ActiveSprintModule(this));
        return modules;
    }

    public static ActiveSprintFragment newInstance() {
        ActiveSprintFragment fragment = new ActiveSprintFragment();
        return fragment;
    }

    public ActiveSprintFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_active_sprint, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mBoardView.setSnapToColumnsWhenScrolling(true);
        mBoardView.setSnapToColumnWhenDragging(true);
        mBoardView.setSnapDragItemToTouch(true);
        mBoardView.setCustomDragItem(new MyDragItem(getActivity(), R.layout.row_card));
        mBoardView.setSnapToColumnInLandscape(false);
        mBoardView.setColumnSnapPosition(BoardView.ColumnSnapPosition.CENTER);
        mBoardView.setBoardListener(new BoardView.BoardListener() {
            @Override
            public void onItemDragStarted(int column, int row) {
//                Toast.makeText(mBoardView.getContext(), "Start - column: " + column + " row: " + row, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemChangedPosition(int oldColumn, int oldRow, int newColumn, int newRow) {
                //Toast.makeText(mBoardView.getContext(), "Position changed - column: " + newColumn + " row: " + newRow, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemChangedColumn(int oldColumn, int newColumn) {
                updateUIHeader(oldColumn);
                updateUIHeader(newColumn);
            }

            @Override
            public void onItemDragEnded(int fromColumn, int fromRow, int toColumn, int toRow) {
                if (fromColumn != toColumn || fromRow != toRow) {
                    Toast.makeText(mBoardView.getContext(), "End - column: " + toColumn + " row: " + toRow, Toast.LENGTH_SHORT).show();
                }
            }
        });
        mBoardView.setBoardCallback(new BoardView.BoardCallback() {
            @Override
            public boolean canDragItemAtPosition(int column, int dragPosition) {
                // Add logic here to prevent an item to be dragged
                return true;
            }

            @Override
            public boolean canDropItemAtPosition(int oldColumn, int oldRow, int newColumn, int newRow) {
                // Add logic here to prevent an item to be dropped
                return true;
            }
        });

        activeSprintPresenter.getActiveSprint(TODO);
        activeSprintPresenter.getActiveSprint(IN_PROGRESS);
        activeSprintPresenter.getActiveSprint(DONE);
    }

    @Override
    public void updateUI(Throwable throwable) {

    }

    private void updateUIHeader(int column){
        TextView txtHeader = (TextView) mBoardView.getHeaderView(column).findViewById(R.id.txt_header);
        switch (column){
            case COLUMN_TODO:
                txtHeader.setText(getString(R.string.todo, mBoardView.getAdapter(column).getItemCount()));
                break;
            case COLUMN_IN_PROGRESS:
                txtHeader.setText(getString(R.string.in_progress, mBoardView.getAdapter(column).getItemCount()));
                break;
            default:
                txtHeader.setText(getString(R.string.done, mBoardView.getAdapter(column).getItemCount()));
                break;
        }
    }

    private void addColumnList(String type, ArrayList<Pair<Long, Item>> itemArray) {
        final View header = View.inflate(getActivity(), R.layout.row_card_header, null);
        TextView txtHeader = ((TextView) header.findViewById(R.id.txt_header));
        if(type.equalsIgnoreCase(TODO)){
            txtHeader.setText(getString(R.string.todo, itemArray.size()));
        } else if(type.equalsIgnoreCase(IN_PROGRESS)){
            txtHeader.setText(getString(R.string.in_progress, itemArray.size()));
        } else {
            txtHeader.setText(getString(R.string.done, itemArray.size()));
        }

        final int column = mColumns;
        final ItemAdapter listAdapter = new ItemAdapter(getContext(), itemArray, R.layout.row_card, R.id.item_layout, true);
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long id = sCreatedItems++;
                Pair item = new Pair<>(id, "Test " + id);
                mBoardView.addItem(column, 0, item, true);
                //mBoardView.moveItem(4, 0, 0, true);
                //mBoardView.removeItem(column, 0);
                //mBoardView.moveItem(0, 0, 1, 3, false);
                //mBoardView.replaceItem(0, 0, item1, true);
//                ((TextView) header.findViewById(R.id.item_count)).setText(String.valueOf(mItemArray.size()));
            }
        });

        mBoardView.addColumnList(listAdapter, header, false);
        mColumns++;
    }

    private ArrayList<Pair<Long, Item>> createSampleData(){
        final ArrayList<Pair<Long, Item>> itemArray = new ArrayList<>();
        int addItems = 10;
        for (int i = 0; i < addItems; i++) {
            String name = "Jira " + i;
            String summary = "Review " + i;
            int issueType = i % 3;
            int priority = i % 3;
            String epicLink = "Epic " + i;
            itemArray.add(new Pair<>(Long.valueOf(i), new Item(Long.valueOf(i), name, summary, issueType, priority, epicLink)));
        }

        return itemArray;
    }

    @Override
    public void showPage(ItemResponse itemResponse, String type) {
        if(type.equalsIgnoreCase(TODO)){
            if(itemResponse == null){
                addColumnList(type, createSampleData());
            }
        } else if(type.equalsIgnoreCase(IN_PROGRESS)){
            if(itemResponse == null){
                addColumnList(type, createSampleData());
            }
        } else {
            if(itemResponse == null){
                addColumnList(type, createSampleData());
            }
        }
    }

    private static class MyDragItem extends DragItem {
        Context context;
        MyDragItem(Context context, int layoutId) {
            super(context, layoutId);
            this.context = context;
        }

        @Override
        public void onBindDragView(View clickedView, View dragView) {
            Item item = (Item) clickedView.getTag();
            bind(dragView, item);

            CardView dragCard = ((CardView) dragView.findViewById(R.id.card));
            CardView clickedCard = ((CardView) clickedView.findViewById(R.id.card));

            dragCard.setMaxCardElevation(40);
            dragCard.setCardElevation(clickedCard.getCardElevation());
            // I know the dragView is a FrameLayout and that is why I can use setForeground below api level 23
//            dragCard.setForeground(clickedView.getResources().getDrawable(R.drawable.card_view_drag_foreground));
        }

        @Override
        public void onMeasureDragView(View clickedView, View dragView) {
            CardView dragCard = ((CardView) dragView.findViewById(R.id.card));
            CardView clickedCard = ((CardView) clickedView.findViewById(R.id.card));
            int widthDiff = dragCard.getPaddingLeft() - clickedCard.getPaddingLeft() + dragCard.getPaddingRight() -
                    clickedCard.getPaddingRight();
            int heightDiff = dragCard.getPaddingTop() - clickedCard.getPaddingTop() + dragCard.getPaddingBottom() -
                    clickedCard.getPaddingBottom();
            int width = clickedView.getMeasuredWidth() + widthDiff;
            int height = clickedView.getMeasuredHeight() + heightDiff;
            dragView.setLayoutParams(new FrameLayout.LayoutParams(width, height));

            int widthSpec = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
            int heightSpec = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);
            dragView.measure(widthSpec, heightSpec);
        }

        @Override
        public void onStartDragAnimation(View dragView) {
            CardView dragCard = ((CardView) dragView.findViewById(R.id.card));
            ObjectAnimator anim = ObjectAnimator.ofFloat(dragCard, "CardElevation", dragCard.getCardElevation(), 40);
            anim.setInterpolator(new DecelerateInterpolator());
            anim.setDuration(ANIMATION_DURATION);
            anim.start();
        }

        @Override
        public void onEndDragAnimation(View dragView) {
            CardView dragCard = ((CardView) dragView.findViewById(R.id.card));
            ObjectAnimator anim = ObjectAnimator.ofFloat(dragCard, "CardElevation", dragCard.getCardElevation(), 6);
            anim.setInterpolator(new DecelerateInterpolator());
            anim.setDuration(ANIMATION_DURATION);
            anim.start();
        }

        public void bind(View rootView, Item item) {
            ImageView imgAvatar = (ImageView) rootView.findViewById(R.id.img_avatar);
            ImageView imgType = (ImageView) rootView.findViewById(R.id.img_type);
            ImageView imgPriority = (ImageView) rootView.findViewById(R.id.img_priority);
            TextView txtName = (TextView) rootView.findViewById(R.id.txt_name);
            TextView txtSummary = (TextView) rootView.findViewById(R.id.txt_summary);
            TextView txtLink = (TextView) rootView.findViewById(R.id.txt_link);
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
    }
}
