import { TaskCardTitleEdit } from "./TaskCardTitleEdit";
import { TaskCardTitleView } from "./TaskCardTitleView";

interface TaskCardTitleProps {
  isEditing: boolean;
  title: string;
  onStartEdit: () => void;
  onChange: (title: string) => void;
  onSave: () => void;
  onCancel: () => void;
}
export function TaskCardTitle({
  title,
  isEditing,
  onStartEdit,
  onChange,
  onSave,
  onCancel,
}: TaskCardTitleProps) {
  if (isEditing) {
    return (
      <TaskCardTitleEdit
        title={title}
        onSave={onSave}
        onChange={onChange}
        onCancel={onCancel}
      />
    );
  }

  return <TaskCardTitleView title={title} onStartEdit={onStartEdit} />;
}
