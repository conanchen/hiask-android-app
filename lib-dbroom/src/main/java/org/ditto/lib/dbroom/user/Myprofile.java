package org.ditto.lib.dbroom.user;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.common.base.Strings;

import org.ditto.lib.dbroom.vo.VoNamecard;
import org.ditto.lib.dbroom.vo.VoProfession;

import java.util.List;

@Entity
public class Myprofile implements Parcelable {
    @PrimaryKey
    public String uuid;
    public int sequence;
    public String type;
    public Content content;
    public long created;

    public Myprofile() {
    }

    private Myprofile(String uuid, int sequence, String type, Content content, long created) {
        this.uuid = uuid;
        this.sequence = sequence;
        this.type = type;
        this.content = content;
        this.created = created;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String uuid;
        private int sequence;
        private String type;
        private Content content;
        private long created;

        Builder() {
        }

        public Myprofile build() {
            String missing = "";
            if (Strings.isNullOrEmpty(uuid)) {
                missing += " uuid";
            }
            if (content == null) {
                missing += " content";
            } else if (content.basic != null) {
                this.type = Myprofile.Basic.class.getSimpleName();
            } else if (content.consultant != null) {
                this.type = Myprofile.Consultant.class.getSimpleName();
            } else if (content.accounting != null) {
                this.type = Myprofile.Accounting.class.getSimpleName();
            } else if (content.visibleRadius != null) {
                this.type = VisibleRadius.class.getSimpleName();
            } else if (content.visibleProfessions != null && content.visibleProfessions.size() != 0) {
                this.type = VoProfession.class.getSimpleName();
            } else if (content.namecard != null) {
                this.type = VoNamecard.class.getSimpleName();
            }
            if (Strings.isNullOrEmpty(type)) {
                missing += " type";
            }

            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }

            return new Myprofile(uuid, sequence, type, content, created);
        }

        public Builder setUuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder setSequence(int sequence) {
            this.sequence = sequence;
            return this;
        }

        public Builder setContent(Content content) {
            this.content = content;
            return this;
        }

        public Builder setCreated(long created) {
            this.created = created;
            return this;
        }
    }

    public static class Content {
        public Basic basic;
        public Consultant consultant;
        public Accounting accounting;
        public VisibleRadius visibleRadius;
        public List<VoProfession> visibleProfessions;
        public VoNamecard namecard;

        public Content() {
        }

        private Content(Basic basic,
                        Consultant consultant,
                        Accounting accounting,
                        VisibleRadius visibleRadius,
                        List<VoProfession> visibleProfessions,
                        VoNamecard namecard) {
            this.basic = basic;
            this.consultant = consultant;
            this.accounting = accounting;
            this.visibleRadius = visibleRadius;
            this.visibleProfessions = visibleProfessions;
            this.namecard = namecard;
        }

        public static Builder builder() {
            return new Builder();
        }

        public static final class Builder {
            private Basic basic;
            private Consultant consultant;
            private Accounting accounting;
            private VisibleRadius visibleRadius;
            private List<VoProfession> visibleProfessions;
            private VoNamecard namecard;

            Builder() {
            }

            public Content build() {
                String missing = "";

                int contentCount = 0;
                if (this.basic != null) {
                    contentCount++;
                }
                if (this.consultant != null) {
                    contentCount++;
                }
                if (this.accounting != null) {
                    contentCount++;
                }
                if (this.visibleRadius != null) {
                    contentCount++;
                }
                if (this.visibleProfessions != null && this.visibleProfessions.size() != 0) {
                    contentCount++;
                }

                if (this.namecard != null) {
                    contentCount++;
                }

                if (contentCount != 1) {
                    missing += " [only 1 allowed]";
                }
                if (!missing.isEmpty()) {
                    throw new IllegalStateException("Missing required properties:" + missing);
                }

                return new Content(basic, consultant, accounting, visibleRadius, visibleProfessions, namecard);
            }

            public Builder setBasic(Basic basic) {
                this.basic = basic;
                return this;
            }

            public Builder setConsultant(Consultant consultant) {
                this.consultant = consultant;
                return this;
            }

            public Builder setAccounting(Accounting accounting) {
                this.accounting = accounting;
                return this;
            }

            public Builder setVisibleRadius(VisibleRadius visibleRadius) {
                this.visibleRadius = visibleRadius;
                return this;
            }

            public Builder setVisibleProfessions(List<VoProfession> visibleProfessions) {
                this.visibleProfessions = visibleProfessions;
                return this;
            }

            public Builder setNamecard(VoNamecard namecard) {
                this.namecard = namecard;
                return this;
            }
        }

    }

    public static class Basic {
        public String icon;
        public String ttNo;
        public String mobilePhone;
        public String nickName;
        public String sex;

        public Basic() {
        }

        private Basic(String icon, String ttNo, String mobilePhone, String nickName, String sex) {
            this.icon = icon;
            this.ttNo = ttNo;
            this.mobilePhone = mobilePhone;
            this.nickName = nickName;
            this.sex = sex;
        }

        public static Builder builder() {
            return new Builder();
        }

        public static final class Builder {
            private String icon;
            private String ttNo;
            private String mobilePhone;
            private String nickName;
            private String sex;

            Builder() {
            }

            public Basic build() {
                String missing = "";

                if (Strings.isNullOrEmpty(ttNo)) {
                    missing += " [ttNo]";
                }

                if (!missing.isEmpty()) {
                    throw new IllegalStateException("Missing required properties:" + missing);
                }

                return new Basic(icon, ttNo, mobilePhone, nickName, sex);
            }

            public Builder setIcon(String icon) {
                this.icon = icon;
                return this;
            }

            public Builder setTtNo(String ttNo) {
                this.ttNo = ttNo;
                return this;
            }

            public Builder setMobilePhone(String mobilePhone) {
                this.mobilePhone = mobilePhone;
                return this;
            }

            public Builder setNickName(String nickName) {
                this.nickName = nickName;
                return this;
            }

            public Builder setSex(String sex) {
                this.sex = sex;
                return this;
            }
        }

    }

    public static class Consultant {
        public int price;
        public String description;

        public Consultant() {
        }

        private Consultant(int price, String description) {
            this.price = price;
            this.description = description;
        }

        public static Builder builder() {
            return new Builder();
        }

        public static final class Builder {
            private int price;
            private String description;

            Builder() {
            }

            public Consultant build() {
                String missing = "";

                if (Strings.isNullOrEmpty(description)) {
                    missing += " description";
                }

                if (!missing.isEmpty()) {
                    throw new IllegalStateException("Missing required properties:" + missing);
                }

                return new Consultant(price, description);
            }

            public Builder setPrice(int price) {
                this.price = price;
                return this;
            }

            public Builder setDescription(String description) {
                this.description = description;
                return this;
            }
        }

    }


    public static class VisibleRadius {
        public int radius;
        public String description;

        public VisibleRadius() {
        }

        private VisibleRadius(int radius, String description) {
            this.radius = radius;
            this.description = description;
        }

        public static Builder builder() {
            return new Builder();
        }

        public static final class Builder {
            private int radius;
            private String description;

            Builder() {
            }

            public VisibleRadius build() {
                String missing = "";

                if (radius < 1) {
                    missing += " radius";
                }

                if (!missing.isEmpty()) {
                    throw new IllegalStateException("Missing required properties:" + missing);
                }

                return new VisibleRadius(radius, description);
            }

            public Builder setRadius(int radius) {
                this.radius = radius;
                return this;
            }

            public Builder setDescription(String description) {
                this.description = description;
                return this;
            }
        }

    }

    public static class Accounting {
        public long balance;
        public long transactionNum;

        public Accounting() {
        }

        private Accounting(long balance, long transactionNum) {
            this.balance = balance;
            this.transactionNum = transactionNum;
        }

        public static Builder builder() {
            return new Builder();
        }

        public static final class Builder {
            private long balance;
            private long transactionNum;


            Builder() {
            }

            public Accounting build() {
                String missing = "";
//
//                if (Strings.isNullOrEmpty(name)) {
//                    missing += " [name]";
//                }

                if (!missing.isEmpty()) {
                    throw new IllegalStateException("Missing required properties:" + missing);
                }

                return new Accounting(balance, transactionNum);
            }

            public Builder setBalance(long balance) {
                this.balance = balance;
                return this;
            }

            public Builder setTransactionNum(long transactionNum) {
                this.transactionNum = transactionNum;
                return this;
            }
        }

    }


    public static final Creator<Myprofile> CREATOR = new Creator<Myprofile>() {
        @Override
        public Myprofile createFromParcel(Parcel in) {
            return new Myprofile(in);
        }

        @Override
        public Myprofile[] newArray(int size) {
            return new Myprofile[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.uuid);
    }

    @Ignore
    protected Myprofile(Parcel in) {
        this.uuid = in.readString();
    }

}
