package com.eli.oneos.model.oneos.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.eli.oneos.R;
import com.eli.oneos.constant.OneOSAPIs;
import com.eli.oneos.model.oneos.OneOSFile;
import com.eli.oneos.model.oneos.user.LoginSession;
import com.eli.oneos.utils.FileUtils;
import com.eli.oneos.widget.sticky.listview.StickyListHeadersAdapter;

import java.util.ArrayList;
import java.util.List;

public class OneOSStickyListAdapter extends OneOSFileBaseAdapter implements StickyListHeadersAdapter/*, SectionIndexer*/ {

    private String[] mSectionLetters = new String[]{};

    public OneOSStickyListAdapter(Context context, List<OneOSFile> fileList, ArrayList<OneOSFile> selectedList, OnMultiChooseClickListener listener, LoginSession mLoginSession) {
        super(context, fileList, selectedList, listener, mLoginSession);
    }

    public void updateSections(String[] sectionLetters) {
        mSectionLetters = sectionLetters;
    }

    @Override
    public int getCount() {
        return mFileList.size();
    }

    @Override
    public Object getItem(int position) {
        return mFileList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
        ImageView mIconView;
        TextView mNameTxt;
        TextView mTimeTxt;
        TextView mSizeTxt;
        CheckBox mSelectCb;
        ImageButton mSelectIBtn;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_listview_filelist, null);

            holder = new ViewHolder();
            holder.mIconView = (ImageView) convertView.findViewById(R.id.iv_icon);
            holder.mNameTxt = (TextView) convertView.findViewById(R.id.txt_name);
            holder.mSelectCb = (CheckBox) convertView.findViewById(R.id.cb_select);
            holder.mSizeTxt = (TextView) convertView.findViewById(R.id.txt_size);
            holder.mTimeTxt = (TextView) convertView.findViewById(R.id.txt_time);
            holder.mSelectIBtn = (ImageButton) convertView.findViewById(R.id.ibtn_select);
            holder.mSelectIBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onClick(v);
                    }
                }
            });

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mSelectIBtn.setTag(position); // for get Select Button Index

        OneOSFile file = mFileList.get(position);
        holder.mNameTxt.setText(file.getName());
        holder.mIconView.setTag(file.getName());
        holder.mTimeTxt.setText(file.getFmtTime());
        holder.mSizeTxt.setText(file.getFmtSize());

        if (file.isEncrypt()) {
            holder.mIconView.setImageResource(R.drawable.icon_file_encrypt);
        } else {
            if (FileUtils.isPictureFile(file.getName())) {
                showPicturePreview(holder.mIconView, file);
            } else if(file.isVideo() && !OneOSAPIs.isOneSpaceX1()){
                showPicturePreview(holder.mIconView, file);
            } else {
                holder.mIconView.setImageResource(file.getIcon());
            }
        }

        if (isMultiChooseModel()) {
            holder.mSelectIBtn.setVisibility(View.GONE);
            holder.mSelectCb.setVisibility(View.VISIBLE);
            holder.mSelectCb.setChecked(getSelectedList().contains(file));
        } else {
            holder.mSelectCb.setVisibility(View.GONE);
            holder.mSelectIBtn.setVisibility(View.VISIBLE);
        }

        return convertView;
    }

    class HeaderViewHolder {
        TextView mHeaderTxt;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;

        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = mInflater.inflate(R.layout.layout_timeline_header, parent, false);
            holder.mHeaderTxt = (TextView) convertView.findViewById(R.id.header);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }

        if (position < mFileList.size()) {
            int section = mFileList.get(position).getSection();
            if (section < mSectionLetters.length) {
                holder.mHeaderTxt.setText(mSectionLetters[section]);
            }
        }

        return convertView;
    }

    /**
     * Remember that these have to be static, postion=1 should always return
     * the same Id that is.
     */
    @Override
    public long getHeaderId(int position) {
        // return the first character of the country as ID because this is what
        // headers are based upon
        if (position >= mFileList.size()) {
            return 0;
        }
        return mFileList.get(position).getSection();
    }

//    @Override
//    public int getPositionForSection(int section) {
//        if (mSectionIndices.length == 0) {
//            return 0;
//        }
//
//        if (section >= mSectionIndices.length) {
//            section = mSectionIndices.length - 1;
//        } else if (section < 0) {
//            section = 0;
//        }
//        return mSectionIndices[section];
//    }
//
//    @Override
//    public int getSectionForPosition(int position) {
//        for (int i = 0; i < mSectionIndices.length; i++) {
//            if (position < mSectionIndices[i]) {
//                return i - 1;
//            }
//        }
//        return mSectionIndices.length - 1;
//    }
//
//    @Override
//    public Object[] getSections() {
//        return mSectionLetters;
//    }

}
