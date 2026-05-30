interface TaskCardTitleEditProps {
  title: string;
  onChange: (title: string) => void;
  onSave: () => void;
  onCancel: () => void;
}
export function TaskCardTitleEdit({
  title,
  onChange,
  onSave,
  onCancel,
}: TaskCardTitleEditProps) {
  return (
    <input
      autoFocus
      value={title}
      onChange={(e) => onChange(e.target.value)}
      onBlur={onSave}
      onKeyDown={(e) => {
        if (e.key === "Enter" && e.currentTarget.value.trim() !== "") onSave();
        if (e.key === "Escape") onCancel();
      }}
      className="w-full border-b border-blue-400 outline-none"
    />
  );
}
