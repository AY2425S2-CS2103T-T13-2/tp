package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.PersonHasAppointmentPredicate;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS_ALL = "Listed all persons";
    public static final String MESSAGE_SUCCESS_FILTERED = "Listed all persons with appointment: %s";

    private final Appointment appointmentFilter;

    public ListCommand(Appointment appointmentFilter) {
        this.appointmentFilter = appointmentFilter;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        if (appointmentFilter == null) {
            model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(MESSAGE_SUCCESS_ALL);
        }

        model.updateFilteredPersonList(new PersonHasAppointmentPredicate(appointmentFilter));
        return new CommandResult(String.format(MESSAGE_SUCCESS_FILTERED, appointmentFilter));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ListCommand)) {
            return false;
        }
        ListCommand otherCommand = (ListCommand) other;
        return appointmentFilter == null ? otherCommand.appointmentFilter == null
                : appointmentFilter.equals(otherCommand.appointmentFilter);
    }
}
