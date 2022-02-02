package parcel_locker_manager.models;

import java.util.UUID;

public class Parcel {
    private UUID id;
    private String name;
    private Size size;
    private double weight;
    private String recipient;
    private String sender;
    private ParcelLocker senderLocker;
    private ParcelLocker recipientLocker;
    private State state;

    public Parcel(String name, Size size, double weight, String recipient, String sender, ParcelLocker senderLocker, ParcelLocker recipientLocker, State state) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.size = size;
        this.weight = weight;
        this.recipient = recipient;
        this.sender = sender;
        this.senderLocker = senderLocker;
        this.recipientLocker = recipientLocker;
        this.state = state;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public ParcelLocker getSenderLocker() {
        return senderLocker;
    }

    public void setSenderLocker(ParcelLocker senderLocker) {
        this.senderLocker = senderLocker;
    }

    public ParcelLocker getRecipientLocker() {
        return recipientLocker;
    }

    public void setRecipientLocker(ParcelLocker recipientLocker) {
        this.recipientLocker = recipientLocker;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return '\n' + "id=" + id +
                ", NAME='" + name + '\'' +
                ", SIZE=" + size +
                ", WEIGHT=" + weight +
                ", RECIPIENT='" + recipient + '\'' +
                ", SENDER='" + sender + '\'' +
                ", SENDER LOCKER='" + senderLocker.getName() + '\'' +
                ", RECIPIENT LOCKER='" + recipientLocker.getName() + '\'' +
                ", STATE='" + state + '\'' + '\n';
    }
}

