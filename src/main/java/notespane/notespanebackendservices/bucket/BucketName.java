package notespane.notespanebackendservices.bucket;
public enum BucketName {
    PROFILE_IMAGE("notespane-posts-feed");
    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}

